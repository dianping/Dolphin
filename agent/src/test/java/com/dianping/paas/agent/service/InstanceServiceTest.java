package com.dianping.paas.agent.service;

import com.dianping.paas.core.dto.request.InstanceStartRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/02 15:39.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:config/spring/appcontext-*.xml")
public class InstanceServiceTest {

    @Resource
    private InstanceService instanceService;

    private InstanceStartRequest instanceStartRequest;

    @Before
    public void setUp() throws Exception {
        instanceStartRequest = new InstanceStartRequest();
        instanceStartRequest.setRepository("docker.dp/test_app:latest");
        instanceStartRequest.setImageId("d1b2d3578fe0");
        instanceStartRequest.setAppName("test_app");
    }

    @Test
    public void testPullImageAndRun() throws Exception {
        instanceService.pullImageAndRun(instanceStartRequest);
    }
}