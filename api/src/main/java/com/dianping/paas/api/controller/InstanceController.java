package com.dianping.paas.api.controller;

import com.dianping.paas.controller.service.InstanceControllerService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/17 15:00.
 */
@RestController
public class InstanceController {

    @Resource
    private InstanceControllerService instanceControllerService;

    @RequestMapping(value = "/apps/{appId}/instance/startups", method = RequestMethod.POST)
    public void startAllInstances(@PathVariable String appId) {
        instanceControllerService.startAllInstances(appId);
    }

    @RequestMapping(value = "/apps/{appId}/instance/shutdowns", method = RequestMethod.POST)
    public void stopAllInstances(@PathVariable String appId) {
        instanceControllerService.stopAllInstances(appId);
    }

    @RequestMapping(value = "/apps/{appId}/instance/scales", method = RequestMethod.POST)
    public void scaleInstance(@PathVariable String appId, int count) {
        instanceControllerService.scaleInstance(appId, count);
    }
}
