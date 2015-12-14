package com.dianping.paas.agent.context.support;

import com.dianping.paas.agent.container.config.CreateContainerPostProcessor;
import com.dianping.paas.agent.context.CreateContainerContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.OrderComparator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 工具类,专门处理与container相关的后置处理
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/14 11:13.
 */
@Component
public class PostProcessorContainerDelegate implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    public void invokeCreateContainerPostProcessors(CreateContainerContext createContainerContext) {
        String[] beanNames = applicationContext.getBeanNamesForType(CreateContainerPostProcessor.class);

        List<CreateContainerPostProcessor> createContainerPostProcessorList = new ArrayList<CreateContainerPostProcessor>();

        for (String beanName : beanNames) {
            CreateContainerPostProcessor createContainerPostProcessor = applicationContext.getBean(beanName, CreateContainerPostProcessor.class);
            createContainerPostProcessorList.add(createContainerPostProcessor);
        }

        OrderComparator.sort(createContainerPostProcessorList);
        invokeCreateContainerPostProcessors(createContainerPostProcessorList, createContainerContext);
    }

    private static void invokeCreateContainerPostProcessors(List<CreateContainerPostProcessor> createContainerPostProcessorList, CreateContainerContext createContainerContext) {
        for (CreateContainerPostProcessor createContainerPostProcessor : createContainerPostProcessorList) {
            createContainerPostProcessor.postProcessContainer(createContainerContext);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
