package com.dianping.paas.agent.container.config;

import com.dianping.paas.agent.context.CreateContainerContext;
import org.springframework.core.Ordered;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/14 11:18.
 */
public interface CreateContainerPostProcessor extends Ordered {

    void postProcessContainer(CreateContainerContext createContainerContext);
}
