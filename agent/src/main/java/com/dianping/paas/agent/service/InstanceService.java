package com.dianping.paas.agent.service;

import com.dianping.paas.core.dto.request.InstanceStartRequest;
import com.dianping.paas.core.dto.response.InstanceStartResponse;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/02 14:57.
 */
public interface InstanceService {
    InstanceStartResponse pullImageAndRun(InstanceStartRequest instanceStartRequest) throws Exception;
}
