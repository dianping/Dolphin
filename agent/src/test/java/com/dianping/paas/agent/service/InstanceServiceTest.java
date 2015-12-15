package com.dianping.paas.agent.service;

import com.dianping.paas.core.dto.request.InstanceRestartRequest;
import com.dianping.paas.core.dto.request.InstanceStartRequest;
import com.dianping.paas.core.dto.request.UpgradeInstanceRequest;
import com.dianping.paas.core.dto.response.InstanceRestartResponse;
import com.dianping.paas.core.dto.response.InstanceStartResponse;
import com.dianping.paas.core.test.Globals;
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

    private UpgradeInstanceRequest upgradeInstanceRequest;

    @Before
    public void setUp() throws Exception {
        instanceStartRequest = new InstanceStartRequest();
        instanceStartRequest.setRepository(Globals.REPOSITORY);
        instanceStartRequest.setImageId(Globals.IMAGE_ID);
        instanceStartRequest.setAppName(Globals.APP_ID);

        instanceRestartRequest = new InstanceRestartRequest();

        upgradeInstanceRequest = new UpgradeInstanceRequest();
        upgradeInstanceRequest.setApp_id(Globals.APP_ID);
        upgradeInstanceRequest.setInstance_index(instanceStartRequest.getInstanceIndex());
        upgradeInstanceRequest.setWebPackageUrl(Globals.WEB_PACKAGE_URL);
        upgradeInstanceRequest.setInstance_index(Globals.INSTANCE_INDEX);
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

    @Test
    /**
     * 前置条件
     * 1. run AppServiceTest.init();
     * 2. start web server
     */
    public void upgrade() throws Exception {
        // which instance_id is running in host
        String instance_id = "3cf629db2494";

        upgradeInstanceRequest.setInstance_id(instance_id);

        instanceService.upgrade(upgradeInstanceRequest);
    }
}