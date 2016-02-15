package com.dianping.paas.core.dal;

import com.dianping.paas.core.dal.dao.InstanceDao;
import com.dianping.paas.core.dal.entity.InstanceEntity;
import com.dianping.paas.core.dto.request.InstanceFilterRequest;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/15 14:05.
 */
@Repository
public class InstanceDal {
    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private InstanceDao instanceDao;

    // TODO 数据库中拉取正在运行的实例
    public List<InstanceEntity> getRunningInstanceList(String app_id, String app_version) {
        return null;
    }

    // TODO 根据条件拉取InstanceList
    public List<InstanceEntity> getFilteredInstanceList(InstanceFilterRequest instanceFilterRequest) {
        return null;
    }

    // TODO 根据appId获取InstanceList
    public List<InstanceEntity> getInstanceByAppId(String appId) {
        return null;
    }

    public int countNonRemovedInstanceByGroupId(long groupId) {
        return instanceDao.countNonRemovedInstanceByGroupId(groupId).size();
    }

    public List<InstanceEntity> findUpgradeableInstances(String appId) {

        return instanceDao.findUpgradeableInstances(appId);
    }

    public long insert(InstanceEntity instance) {
        return instanceDao.insert(instance);
    }
}
