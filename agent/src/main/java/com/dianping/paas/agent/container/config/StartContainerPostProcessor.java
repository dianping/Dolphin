package com.dianping.paas.agent.container.config;

import com.dianping.paas.agent.context.StartContainerContext;
import org.springframework.core.Ordered;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/14 15:36.
 */
public interface StartContainerPostProcessor extends Ordered {

    void beforeStartContainer(StartContainerContext context);

    /**
     * 挂载SLB节点等
     */
    void afterStartContainer(StartContainerContext context);
}
