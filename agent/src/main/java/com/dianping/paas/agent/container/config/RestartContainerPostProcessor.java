package com.dianping.paas.agent.container.config;

import com.dianping.paas.agent.context.RestartContainerContext;
import org.springframework.core.Ordered;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/14 15:00.
 */
public interface RestartContainerPostProcessor extends Ordered {

    /**
     * 摘掉SLB节点等
     */
    void beforeRestartContainer(RestartContainerContext restartContainerContext);

    /**
     * 挂载SLB节点等
     */
    void afterRestartContainer(RestartContainerContext restartContainerContext);

}
