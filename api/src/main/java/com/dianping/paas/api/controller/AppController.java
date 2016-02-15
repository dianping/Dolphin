package com.dianping.paas.api.controller;

import com.dianping.paas.controller.service.AppControllerService;
import com.dianping.paas.core.config.ConfigManager;
import com.dianping.paas.core.dal.entity.AppEntity;
import com.dianping.paas.core.dto.request.AppInitRequest;
import com.dianping.paas.core.dto.response.AsyncOperationResponse;
import com.dianping.paas.core.extension.ExtensionLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by yuchao on 15/11/2.
 */
@RestController
@RequestMapping("/v1/apps")
public class AppController {

    public static final Logger logger = LogManager.getLogger(AppController.class);

    @Resource
    private AppControllerService appControllerService;

    private ConfigManager configManager = ExtensionLoader.getExtension(ConfigManager.class);

    @RequestMapping(method = RequestMethod.GET)
    public List<AppEntity> getAll() {
        logger.info("/v1/apps");
        String dockerIp = configManager.getDockerIp();
        logger.info("dockerIp is " + dockerIp);
        return appControllerService.findAllApp();
    }

    @RequestMapping(value = "/init", method = RequestMethod.POST)
    @ResponseBody
    public AsyncOperationResponse initApp(@RequestBody AppInitRequest appInitRequest) {
        return appControllerService.initApp(appInitRequest);
    }

}
