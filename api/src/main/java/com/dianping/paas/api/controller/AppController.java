package com.dianping.paas.api.controller;

import com.dianping.paas.controller.service.AppService;
import com.dianping.paas.core.dto.request.AppInitRequest;
import com.dianping.paas.core.dto.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * Created by yuchao on 15/11/2.
 */
@RestController
@RequestMapping("/v1/apps")
public class AppController {

    public static final Logger logger = LogManager.getLogger(AppController.class);
//    @Resource
//    private AppService appService;
//
//
//    private ConfigManager configManager = ExtensionLoader.getExtension(ConfigManager.class);
//
//    @RequestMapping(method = RequestMethod.GET)
//    public List<AppEntity> getAll() {
//        logger.info("/v1/apps");
//        String dockerIp = configManager.getDockerIp();
//        logger.info("dockerIp is " + dockerIp);
//        return appService.getAll();
//    }
//
//
//    @RequestMapping(method = RequestMethod.POST)
//    public void init(@RequestBody AppInitRequest appInfo) {
//        appService.init(appInfo);
//    }

    @Resource
    private AppService appService;

    @RequestMapping(method = RequestMethod.POST)
    public Response initApp(@RequestBody AppInitRequest appInitRequest) {
        if (validAppInitRequest(appInitRequest)) {
            appService.initApp(appInitRequest);
        }
        return null;
    }

    private boolean validAppInitRequest(AppInitRequest appInitRequest) {
        return true;
    }

}
