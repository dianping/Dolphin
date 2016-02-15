package com.dianping.paas.controller.message.facade;

import com.dianping.paas.controller.executor.context.InstanceDeployContext;
import com.dianping.paas.core.dto.response.NewInstanceAndDeployWarResponse;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 16/2/15.
 */
public interface AgentFacade {

    NewInstanceAndDeployWarResponse create(InstanceDeployContext instanceDeployContext);

}
