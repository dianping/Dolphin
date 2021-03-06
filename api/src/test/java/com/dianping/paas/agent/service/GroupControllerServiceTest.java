package com.dianping.paas.agent.service;

import com.dianping.paas.controller.service.GroupControllerService;
import com.dianping.paas.test.SpringTest;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by yuchao on 12/7/15.
 */
public class GroupControllerServiceTest extends SpringTest {

    private String app_id;

    private String app_version;

    @Resource
    private GroupControllerService groupControllerService;

    @Before
    public void setUp() throws Exception {
        app_id = "dp";
        app_version = "latest";
    }

    @Test
    public void testUpgradeInstances() throws Exception {
        // need /data/paas/webpackages/token token is zip file && start web server
        groupControllerService.upgradeInstances(app_id, app_version);

        Thread.sleep(10000);
    }
}