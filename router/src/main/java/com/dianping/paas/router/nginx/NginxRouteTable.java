package com.dianping.paas.router.nginx;

import com.dianping.paas.config.ConfigManager;
import com.dianping.paas.extension.ExtensionLoader;
import com.dianping.paas.router.RouteEntry;
import com.dianping.paas.router.RouteTable;
import com.dianping.paas.util.JsonHttpUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/11/16 14:57.
 */
@Component
public class NginxRouteTable implements RouteTable {

    public static final Logger logger = LogManager.getLogger(NginxRouteTable.class);

    private static final int DEFAULT_TRY_COUNT = 3;
    private ConfigManager configManager = ExtensionLoader.getExtension(ConfigManager.class);

    public boolean add(RouteEntry routeEntry) {
        String upstream = routeEntry.getAppId();
        Assert.notNull(upstream);

        boolean success = false;
        NginxSlbRequest nginxSlbRequest = NginxSlbRequest.newFrom(routeEntry);

        logger.info(String.format("Add RouteEntry, AppId: %s, Request: %s", routeEntry.getAppId(), nginxSlbRequest));

        NginxSlbResponse nginxSlbResponse = doAdd(nginxSlbRequest, upstream, DEFAULT_TRY_COUNT);
        if (nginxSlbResponse != null && nginxSlbResponse.success()) {
            success = deploy(nginxSlbResponse, upstream, DEFAULT_TRY_COUNT);
        }

        if (!success) {
            logger.error(String.format("Add RouteEntry Error! Request: %s, Response: %s", nginxSlbRequest, nginxSlbResponse));
        }

        return success;
    }

    private NginxSlbResponse doAdd(NginxSlbRequest nginxSlbRequest, String upstream, int tryCount) {
        NginxSlbResponse nginxSlbResponse = null;
        try {
            String url = buildAddMemberUrl(upstream);
            List<NginxSlbRequest> requestBodyObject = Collections.singletonList(nginxSlbRequest);
            nginxSlbResponse = JsonHttpUtil.postOne(url, requestBodyObject, NginxSlbResponse.class);
        } catch (Exception e) {
            logger.error(String.format("Http Exception When SLB Post To Nginx Server, Request: %s", nginxSlbRequest), e);
            if (tryCount > 0) {
                logger.info(String.format("Http Exception When SLB Post, try again with retries == %s", tryCount));
                return doAdd(nginxSlbRequest, upstream, tryCount - 1);
            }
        }
        return nginxSlbResponse;
    }

    private boolean deploy(NginxSlbResponse nginxSlbResponse, String upstream, int tryCount) {
        boolean success = false;

        if (nginxSlbResponse.success()) {
            success = doDeploy(upstream);
        } else if (nginxSlbResponse.poolNotFound()) {
            success = true;
            logger.info(String.format("SLB pool %s not found, will treat as success", upstream));
        } else if (nginxSlbResponse.unknownError()) {
            if (tryCount > 0) {
                logger.info(String.format("unknown environment error SLB pool %s , try again with retries == %s",
                        upstream, tryCount));
                return deploy(nginxSlbResponse, upstream, tryCount);
            }
        }

        return success;
    }

    private boolean doDeploy(String upstream) {
        boolean success = false;

        try {
            logger.info("Deploying upstream " + upstream);
            String url = buildDeployUrl(upstream);

            NginxSlbResponse slbResponse = JsonHttpUtil.postOne(url, NginxSlbResponse.class);

            if (slbResponse.success()) {
                success = true;
                logger.info(String.format("SLB Deploy Success(TaskID: %d)", slbResponse.getTaskId()));
            } else if (StringUtils.hasText(slbResponse.getMessage())) {
                logger.error(String.format("Error When deploying, Request is %s", slbResponse));
            }

        } catch (Exception e) {
            logger.error("SLB Deploy Exception", e);
        }

        return success;
    }

    public boolean remove(RouteEntry entry) {
        return false;
    }

    public boolean enable(RouteEntry entry) {
        return false;
    }

    public boolean disable(RouteEntry entry) {
        return false;
    }


    private String buildAddMemberUrl(String upstream) {
        return String.format("http://%s/api/v2/pool/%s/addMember", configManager.getSlbDomain(), upstream);
    }

    private String buildDeployUrl(String upstream) {
        return String.format("http://%s/api/pool/%s/deploy", configManager.getSlbDomain(), upstream);
    }
}
