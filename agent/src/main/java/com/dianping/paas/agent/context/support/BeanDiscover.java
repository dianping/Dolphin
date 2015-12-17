package com.dianping.paas.agent.context.support;

import com.google.common.collect.Lists;
import org.springframework.context.ApplicationContext;
import org.springframework.core.OrderComparator;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/14 15:45.
 */
@Component
public class BeanDiscover {
    @Resource
    private ApplicationContext applicationContext;

    public <T> List<T> getBeanList(Class<T> type) {
        String[] beanNames = applicationContext.getBeanNamesForType(type);

        List<T> createContainerPostProcessorList = Lists.newArrayList();

        for (String beanName : beanNames) {
            T containerPostProcessor = applicationContext.getBean(beanName, type);
            createContainerPostProcessorList.add(containerPostProcessor);
        }

        return createContainerPostProcessorList;
    }


    public <T extends Ordered> List<T> getSortedBeanList(Class<T> type) {
        List<T> beanList = getBeanList(type);
        OrderComparator.sort(beanList);

        return beanList;
    }
}
