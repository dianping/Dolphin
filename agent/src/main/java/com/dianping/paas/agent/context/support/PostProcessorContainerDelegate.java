package com.dianping.paas.agent.context.support;

import com.dianping.paas.agent.container.config.CreateContainerPostProcessor;
import com.dianping.paas.agent.container.config.Period;
import com.dianping.paas.agent.container.config.RestartContainerPostProcessor;
import com.dianping.paas.agent.container.config.StartContainerPostProcessor;
import com.dianping.paas.agent.context.CreateContainerContext;
import com.dianping.paas.agent.context.RestartContainerContext;
import com.dianping.paas.agent.context.StartContainerContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 工具类,专门处理与container相关的后置处理
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/14 11:13.
 */
@Component
public class PostProcessorContainerDelegate {

    @Resource
    private BeanDiscover beanDiscover;

    //  ________________________________________________________________________
    // |                                                                        |
    // | do something before or after create container                          |
    // |________________________________________________________________________|
    //

    public void postProcessBeforeCreateContainer(CreateContainerContext context) {
        invokeCreateContainerPostProcessors(context, Period.BEFORE);
    }

    public void postProcessAfterCreateContainer(CreateContainerContext context) {
        invokeCreateContainerPostProcessors(context, Period.AFTER);
    }

    private void invokeCreateContainerPostProcessors(CreateContainerContext context, Period period) {
        List<CreateContainerPostProcessor> postProcessors = beanDiscover.getSortedBeanList(CreateContainerPostProcessor.class);

        invokeCreateContainerPostProcessors(postProcessors, context, period);
    }

    private void invokeRestartContainerPostProcessors(List<RestartContainerPostProcessor> postProcessors, RestartContainerContext context, Period period) {
        for (RestartContainerPostProcessor postProcessor : postProcessors) {
            if (Period.BEFORE.equals(period)) {
                postProcessor.beforeRestartContainer(context);
            } else if (Period.AFTER.equals(period)) {
                postProcessor.afterRestartContainer(context);
            }
        }
    }


    //  ________________________________________________________________________
    // |                                                                        |
    // | do something before or after start container                           |
    // |________________________________________________________________________|
    //

    public void postProcessBeforeStartContainer(StartContainerContext context) {
        invokeStartContainerPostProcessors(context, Period.BEFORE);
    }

    public void postProcessAfterStartContainer(StartContainerContext context) {
        invokeStartContainerPostProcessors(context, Period.AFTER);
    }

    private void invokeStartContainerPostProcessors(StartContainerContext context, Period period) {
        List<StartContainerPostProcessor> postProcessors = beanDiscover.getSortedBeanList(StartContainerPostProcessor.class);

        invokeStartContainerPostProcessors(postProcessors, context, period);
    }

    private void invokeStartContainerPostProcessors(List<StartContainerPostProcessor> postProcessors, StartContainerContext context, Period period) {
        for (StartContainerPostProcessor postProcessor : postProcessors) {
            if (Period.BEFORE.equals(period)) {
                postProcessor.beforeStartContainer(context);
            } else if (Period.AFTER.equals(period)) {
                postProcessor.afterStartContainer(context);
            }
        }
    }


    //  ________________________________________________________________________
    // |                                                                        |
    // | do something before or after restart container                         |
    // |________________________________________________________________________|
    //

    public void postProcessBeforeRestartContainer(RestartContainerContext context) {
        invokeRestartContainerPostProcessors(context, Period.BEFORE);
    }

    public void postProcessAfterRestartContainer(RestartContainerContext context) {
        invokeRestartContainerPostProcessors(context, Period.AFTER);
    }

    private void invokeRestartContainerPostProcessors(RestartContainerContext context, Period period) {
        List<RestartContainerPostProcessor> postProcessors = beanDiscover.getSortedBeanList(RestartContainerPostProcessor.class);

        invokeRestartContainerPostProcessors(postProcessors, context, period);
    }

    private static void invokeCreateContainerPostProcessors(List<CreateContainerPostProcessor> postProcessors, CreateContainerContext context, Period period) {
        for (CreateContainerPostProcessor postProcessor : postProcessors) {
            if (Period.BEFORE.equals(period)) {
                postProcessor.beforeCreateContainer(context);
            } else if (Period.AFTER.equals(period)) {
                postProcessor.beforeCreateContainer(context);
            }
        }
    }
}
