package com.dianping.paas.agent.context;

import com.dianping.paas.core.dto.request.InstanceStartRequest;
import com.github.dockerjava.api.command.StartContainerCmd;
import lombok.Data;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/14 15:31.
 */
@Data
public class StartContainerContext {
    private InstanceStartRequest instanceStartRequest;

    private StartContainerCmd startContainerCmd;
}
