package com.dianping.paas.agent.service;

import com.dianping.paas.core.dto.request.InstanceRemoveRequest;
import com.dianping.paas.core.dto.request.InstanceRestartRequest;
import com.dianping.paas.core.dto.request.InstanceStartRequest;
import com.dianping.paas.core.dto.request.InstanceStopRequest;
import com.dianping.paas.core.dto.response.InstanceRemoveResponse;
import com.dianping.paas.core.dto.response.InstanceRestartResponse;
import com.dianping.paas.core.dto.response.InstanceStartResponse;
import com.dianping.paas.core.dto.response.InstanceStopResponse;

/**
 * 直接与docker中的容器打交道的类
 * Created by yuchao on 12/4/15.
 */
public interface DockerContainerService {
    boolean createContainer(InstanceStartRequest request, InstanceStartResponse response);

    void startContainer(InstanceStartRequest request, InstanceStartResponse response);

    void restartContainer(InstanceRestartRequest request, InstanceRestartResponse response);

    void stopContainer(InstanceStopRequest request, InstanceStopResponse response);

    /** force remove no matter container is running */
    void removeContainer(InstanceRemoveRequest request, InstanceRemoveResponse response);
}
