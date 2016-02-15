package com.dianping.paas.controller.strategy;

import com.dianping.paas.core.dal.InstanceDal;
import com.dianping.paas.core.dal.entity.InstanceEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 16/2/3.
 */

@Service
public class DefaultInstanceIndexAllocater implements InstanceIndexAllocater {

    @Resource
    private InstanceDal instanceDal;

    @Override
    public List<Long> allocateInstanceIndex(String appId, int instanceCount) {
        List<InstanceEntity> instances = instanceDal.findUpgradeableInstances(appId);
        return getAvailableInstanceIndex(instances, instanceCount);
    }

    private List<Long> getAvailableInstanceIndex(List<InstanceEntity> instances, int instanceCount) {
        TreeSet<Long> sortedExistedIndexes = new TreeSet<Long>();
        for (InstanceEntity instance : instances) {
            sortedExistedIndexes.add(instance.getInstanceIndex());
        }

        List<Long> values = new ArrayList<Long>(instanceCount);

        long largestIndex = sortedExistedIndexes.size() > 0 ? sortedExistedIndexes.last() : 0;
        for (long index = 1; index < largestIndex; index++) {
            if (values.size() == instanceCount) {
                return values;
            }
            if (!sortedExistedIndexes.contains(index)) {
                values.add(index);
            }
        }

        while (values.size() < instanceCount) {
            values.add(++largestIndex);
        }

        return values;
    }

}
