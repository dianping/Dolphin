package com.dianping.paas.controller;

import com.dianping.paas.config.ConfigManager;
import com.dianping.paas.entity.AppEntity;
import com.dianping.paas.extension.ExtensionLoader;
import com.dianping.paas.service.AppService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    private AppService appService;

    private ConfigManager configManager = ExtensionLoader.getExtension(ConfigManager.class);

    @RequestMapping(method = RequestMethod.GET)
    public List<AppEntity> getAll() {
        logger.info("-> /v1/apps");
        String dockerIp = configManager.getDockerIp();
        logger.info("dockerIp is " + dockerIp);
        return appService.getAll();
    }
}
