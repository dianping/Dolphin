package com.dianping.paas.agent.service;

import com.dianping.paas.core.dto.AppInfo;
import com.dianping.paas.core.service.AppService;
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
public class AppServiceTest {
    @Resource
    private AppService appService;

    private AppInfo appInfo;

    @Before
    public void setUp() throws Exception {
        appInfo = new AppInfo();
        appInfo.setApp_Id("test_app_4");
        appInfo.setImage_type("busybox");
    }

    @Test
    public void testInit() throws Exception {
        appService.init(appInfo);
        Thread.sleep(30000);
    }
}