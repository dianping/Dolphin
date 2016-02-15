package com.dianping.paas.controller.message.facade.impl;

import com.dianping.paas.controller.executor.context.InstanceDeployContext;
import com.dianping.paas.controller.message.facade.AgentFacade;
import com.dianping.paas.core.dto.response.NewInstanceAndDeployWarResponse;
import org.springframework.stereotype.Component;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 16/2/15.
 */
@Component
public class AgentFacadeImpl implements AgentFacade {
    @Override
    public NewInstanceAndDeployWarResponse create(InstanceDeployContext instanceDeployContext) {
        return null;
    }
}
