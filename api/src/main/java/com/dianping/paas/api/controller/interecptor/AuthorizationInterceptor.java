package com.dianping.paas.api.controller.interecptor;

import com.dianping.paas.core.config.ConfigManager;
import com.dianping.paas.api.util.IpUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/11/06 10:08.
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter implements InitializingBean {
    private final static Logger logger = LogManager.getLogger(AuthorizationInterceptor.class);

    @Resource
    private ConfigManager configManager;

    private List<String> ipWhiteList;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ipAddress = IpUtil.getIpAddress(request);

        boolean authorized = ipWhiteList.contains("0.0.0.0") || ipWhiteList.contains(ipAddress);
        if (!authorized) {
            logger.warn(String.format("ip[%s] is not authorized!", ipAddress));
        }

        return authorized;
    }

    public void afterPropertiesSet() throws Exception {
        initIpWhiteList();
    }

    private void initIpWhiteList() {
        ipWhiteList = configManager.getIpWhiteList();
    }
}
