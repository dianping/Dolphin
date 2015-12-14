package com.dianping.paas.agent.context;

import com.dianping.paas.core.dto.request.InstanceRestartRequest;
import com.github.dockerjava.api.command.RestartContainerCmd;
import lombok.Data;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/14 14:51.
 */
@Data
public class RestartContainerContext {
    private InstanceRestartRequest instanceRestartRequest;

    private RestartContainerCmd restartContainerCmd;
}
