package com.dianping.paas.controller.strategy;


import com.dianping.paas.core.dal.InstanceDal;
import com.dianping.paas.core.dal.InstanceGroupDal;
import com.dianping.paas.core.dal.entity.InstanceGroupEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 16/2/3.
 */

@Service
public class DefaultInstanceGroupStrategy implements InstanceGroupStrategy {


    @Resource
    private InstanceDal instanceDal;

    @Resource
    private InstanceGroupDal instanceGroupDal;

    @Override
    public List<Long> allocateInstanceGroup(String appId, int instanceCount) {
        List<InstanceGroupEntity> groups = instanceGroupDal.findInstanceGroupByAppId(appId);

        if (groups.size() == 0) {
            throw new RuntimeException("No instance group record found in db");
        }

        List<Long> groupIds = new ArrayList<Long>();
        Map<Long, Integer> preAllocated = new HashMap<Long, Integer>();

        for (int i = 0; i < instanceCount; i++) {
            long groupId = doAllocateGroup(appId, groups, preAllocated);
            increPrellocatedCount(preAllocated, groupId);
            groupIds.add(groupId);
        }

        return groupIds;
    }

    private long doAllocateGroup(String appId, List<InstanceGroupEntity> groups, Map<Long, Integer> preAllocated) {

        for (InstanceGroupEntity group : groups) {
            if (!groupFull(group, preAllocated)) {
                return group.getId();
            }
        }

        InstanceGroupEntity createdInstanceGroup = new InstanceGroupEntity();
        createdInstanceGroup.setAppId(appId);
        createdInstanceGroup.setMaxInstance(10);
        createdInstanceGroup.setName("paas group" + (groups.size() + 1));
        instanceGroupDal.insert(createdInstanceGroup);
        groups.add(createdInstanceGroup);

        return createdInstanceGroup.getId();

    }

    private boolean groupFull(InstanceGroupEntity instanceGroup, Map<Long, Integer> preAllocated) {
        Integer preAllocatedCount = preAllocated.get(instanceGroup.getId());
        preAllocatedCount = preAllocatedCount == null ? 0 : preAllocatedCount;
        return instanceDal.countNonRemovedInstanceByGroupId(instanceGroup.getId()) + preAllocatedCount >= instanceGroup.getMaxInstance();
    }

    private void increPrellocatedCount(Map<Long, Integer> preAllocated, long groupId) {
        Integer oldCount = preAllocated.get(groupId);
        int newCount;
        if (oldCount == null) {
            newCount = 1;
        } else {
            newCount = oldCount + 1;
        }
        preAllocated.put(groupId, newCount);
    }

}
