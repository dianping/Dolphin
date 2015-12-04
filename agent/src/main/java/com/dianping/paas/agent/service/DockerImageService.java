package com.dianping.paas.agent.service;

import com.dianping.paas.core.dto.request.InstanceStartRequest;
import com.dianping.paas.core.dto.response.InstanceStartResponse;

/**
 * 直接与docker中的image打交道的类
 * Created by yuchao on 12/4/15.
 */
public interface DockerImageService {
    void pull(InstanceStartRequest request, InstanceStartResponse response);

    void run(InstanceStartRequest request, InstanceStartResponse response);
}
