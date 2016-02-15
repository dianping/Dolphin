package com.dianping.paas.agent.service;

import com.dianping.paas.controller.service.AppControllerService;
import com.dianping.paas.core.dto.request.AppInitRequest;
import com.dianping.paas.core.test.Globals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by yuchao on 12/1/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:config/spring/appcontext-*.xml")
public class AppControllerServiceTest {
    @Resource
    private AppControllerService appControllerService;

    private AppInitRequest appInitRequest;

    @Before
    public void setUp() throws Exception {
        appInitRequest = new AppInitRequest();
        appInitRequest.setAppId(Globals.APP_ID);
        appInitRequest.setImage(Globals.IMAGE);
        appInitRequest.setType(Globals.APP_TYPE);
        appInitRequest.setOwner(Globals.APP_OWNER);
        appInitRequest.setInstanceCount(1);
        appInitRequest.setAppPlanId(1);

    }

    @Test
    public void testInit() throws Exception {
        appControllerService.initApp(appInitRequest);
        Thread.sleep(30000);
    }
}