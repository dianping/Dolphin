package com.dianping.paas.router.nginx;

import com.dianping.paas.core.config.ConfigManager;
import com.dianping.paas.core.extension.ExtensionLoader;
import com.dianping.paas.router.RouteTable;
import com.dianping.paas.router.nginx.url.NginxUrlBuilder;
import com.dianping.paas.core.util.JsonHttpUtil;
import com.dianping.paas.router.RouteEntry;
import com.dianping.paas.core.util.ArrayUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/11/16 14:57.
 */
@Component
public class NginxRouteTable implements RouteTable {

    public static final Logger logger = LogManager.getLogger(NginxRouteTable.class);
    private static final int DEFAULT_HTTP_TRY_COUNT = 1;

    @Resource
    private NginxUrlBuilder nginxUrlBuilder;
    private ConfigManager configManager = ExtensionLoader.getExtension(ConfigManager.class);

    public boolean add(RouteEntry routeEntry) {
        logger.info(String.format("add routeEntry %s", routeEntry));

        String url = nginxUrlBuilder.buildAddMemberUrl(routeEntry.getAppId());

        return route(url, routeEntry);
    }

    public boolean remove(RouteEntry routeEntry) {
        logger.info(String.format("remove routeEntry %s", routeEntry));

        String url = nginxUrlBuilder.buildDelMemberUrl(routeEntry.getAppId());

        return route(url, routeEntry, NginxSlbResponse.MEMBER_NOT_FOUND, NginxSlbResponse.NOT_SUCCESS_ALL);
    }

    public boolean enable(RouteEntry routeEntry) {
        routeEntry.enable();

        logger.info(String.format("Enable routeEntry %s", routeEntry));

        String url = nginxUrlBuilder.buildUpdateMemberUrl(routeEntry.getAppId());

        return !route(url, routeEntry) && add(routeEntry);
    }

    public boolean disable(RouteEntry routeEntry) {
        if (!configManager.shouldNotifySlb()) {
            logger.info("ShouldNotifySlb is false, skip and return.");
            return true;
        }

        logger.info(String.format("Disable routeEntry %s", routeEntry));

        routeEntry.disable();
        String url = nginxUrlBuilder.buildUpdateMemberUrl(routeEntry.getAppId());

        return route(url, routeEntry, NginxSlbResponse.MEMBER_NOT_FOUND);
    }

    private boolean deploy(NginxSlbRequest nginxSlbRequest, String upstream) {
        String url = nginxUrlBuilder.buildDeployUrl(upstream);

        logger.info(String.format("Deploy nginxSlbRequest = %s, upstream = %s", nginxSlbRequest, upstream));

        return getResponse(url, nginxSlbRequest, DEFAULT_HTTP_TRY_COUNT).success();
    }

    /**
     * 所有路由相关的操作都调用这个方法处理
     *
     * @param url                   路由操作的url
     * @param routeEntry            路由实体
     * @param ignoredErrorCodeArray 忽略的错误码, example [1,2,3] 返回码为1或2或3 都表示success,默认0表示success
     * @return {@code true} 表示操作成功, {@code false} 表示操作失败
     */
    private boolean route(String url, RouteEntry routeEntry, int... ignoredErrorCodeArray) {
        String upstream = routeEntry.getAppId();
        NginxSlbRequest nginxSlbRequest = null;
        NginxSlbResponse nginxSlbResponse = null;

        if (upstream != null) {
            nginxSlbRequest = NginxSlbRequest.createInstanceFrom(routeEntry);

            logger.info(String.format("Route operation begin, url = %s, NginxSlbRequest = %s ", url, nginxSlbRequest));

            nginxSlbResponse = getResponse(url, nginxSlbRequest, DEFAULT_HTTP_TRY_COUNT);

            logger.info(String.format("Route operation end, url = %s, NginxSlbResponse = %s ", url, nginxSlbResponse));

            /** 所有的操作都忽略poolNotFound该错误并终止, 特定的操作会忽略特定的错误并终止*/
            if (nginxSlbResponse.poolNotFound() || ArrayUtil.contains(ignoredErrorCodeArray, nginxSlbResponse.getErrorCode())) {
                logger.info(String.format("Ignore error %s when request %s!", nginxSlbResponse.getErrorDesc(), url));
                return true;
            }

            /** 操作成功才会部署 */
            if (nginxSlbResponse.success()) {
                return deploy(nginxSlbRequest, upstream);
            }
        }

        logger.error(String.format("Error occurs when do route operation! NginxSlbRequest = %s, NginxSlbResponse = %s!", nginxSlbRequest, nginxSlbResponse));

        return false;
    }

    /**
     * 给一个指定的URL发送请求获取响应, 直到没有出现未知错误
     *
     * @param url             请求的URL
     * @param nginxSlbRequest 请求对象
     * @param tryCount        尝试次数
     * @return 响应对象
     */
    private NginxSlbResponse getResponse(String url, NginxSlbRequest nginxSlbRequest, int tryCount) {
        NginxSlbResponse nginxSlbResponse = new NginxSlbResponse();
        List<NginxSlbRequest> requestBodyObject = Collections.singletonList(nginxSlbRequest);

        for (int i = 0; i < tryCount; i++) {
            try {
                nginxSlbResponse = JsonHttpUtil.postOne(url, requestBodyObject, NginxSlbResponse.class);
                if (!nginxSlbResponse.unknownError()) {
                    break;
                }
            } catch (Exception e) {
                logger.error(String.format("Http Exception When Post to %s, Request is %s, TryCount is %s", url, nginxSlbRequest, tryCount), e);
            }
        }

        return nginxSlbResponse;
    }
}
