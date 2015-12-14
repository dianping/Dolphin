package com.dianping.paas.agent.context;

import com.dianping.paas.core.dto.request.InstanceStartRequest;
import com.github.dockerjava.api.command.CreateContainerCmd;
import lombok.Data;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/14 11:10.
 */
@Data
public class CreateContainerContext {

    private CreateContainerCmd createContainerCmd;

    private InstanceStartRequest instanceStartRequest;

}
