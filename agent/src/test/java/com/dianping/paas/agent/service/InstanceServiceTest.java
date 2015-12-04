package com.dianping.paas.agent.service;

import com.dianping.paas.core.dto.request.InstanceRestartRequest;
import com.dianping.paas.core.dto.request.InstanceStartRequest;
import com.dianping.paas.core.dto.response.InstanceRestartResponse;
import com.dianping.paas.core.dto.response.InstanceStartResponse;
import org.junit.Assert;
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

    private InstanceRestartRequest instanceRestartRequest;

    @Before
    public void setUp() throws Exception {
        instanceStartRequest = new InstanceStartRequest();
        instanceStartRequest.setRepository("docker.dp/dp:latest");
        instanceStartRequest.setImageId("ab09aec5fa98");
        instanceStartRequest.setAppName("dp");

        instanceRestartRequest = new InstanceRestartRequest();
    }

    @Test
    public void pullImageAndRun() throws Exception {
        InstanceStartResponse response = instanceService.pullImageAndRun(instanceStartRequest);

        Assert.assertTrue(response.isSuccess());
    }

    @Test
    public void restartInstance() throws Exception {
        InstanceStartResponse instanceStartResponse = instanceService.pullImageAndRun(instanceStartRequest);

        instanceRestartRequest.setContainerId(instanceStartResponse.getContainerId());
        InstanceRestartResponse instanceRestartResponse = instanceService.restartInstance(instanceRestartRequest);

        Assert.assertTrue(instanceRestartResponse.isSuccess());
    }
}