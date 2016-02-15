package com.dianping.paas.core.dal;

import com.dianping.paas.core.dal.dao.InstanceGroupDao;
import com.dianping.paas.core.dal.entity.InstanceGroupEntity;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 15/12/17.
 */
@Repository
public class InstanceGroupDal {
    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private InstanceGroupDao instanceGroupDao;

    public long insert(InstanceGroupEntity instanceGroup) {
       return instanceGroupDao.insert(instanceGroup);
    }


    public List<InstanceGroupEntity> findInstanceGroupByAppId(String appId) {
        return instanceGroupDao.findInstanceGroupByAppId(appId);
    }
}
