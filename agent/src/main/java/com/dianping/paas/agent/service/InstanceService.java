package com.dianping.paas.agent.service;

import com.dianping.paas.core.dto.request.*;
import com.dianping.paas.core.dto.response.InstanceRemoveResponse;
import com.dianping.paas.core.dto.response.InstanceRestartResponse;
import com.dianping.paas.core.dto.response.InstanceStartResponse;
import com.dianping.paas.core.dto.response.InstanceStopResponse;

import java.io.IOException;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/02 14:57.
 */
public interface InstanceService {
    InstanceStartResponse pullImageAndRun(InstanceStartRequest instanceStartRequest);

    InstanceStartResponse startInstance(InstanceStartRequest request);

    InstanceRestartResponse restartInstance(InstanceRestartRequest instanceRestartRequest);

    InstanceStopResponse stopInstance(InstanceStopRequest request);

    InstanceRemoveResponse removeInstance(InstanceRemoveRequest request);

    void upgrade(UpgradeInstanceRequest upgradeInstanceRequest) throws IOException;
}
