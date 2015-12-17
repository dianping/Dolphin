package com.dianping.paas.api.controller;

import com.dianping.paas.controller.service.GroupControllerService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by yuchao on 12/7/15.
 */
@RestController
public class GroupController {

    @Resource
    private GroupControllerService groupControllerService;

    @RequestMapping(value = "/apps/{app_id}/groups", method = RequestMethod.PUT)
    private void upgradeInstances(@PathVariable("app_id") String app_id, String app_version) {
        groupControllerService.upgradeInstances(app_id, app_version);
    }
}
