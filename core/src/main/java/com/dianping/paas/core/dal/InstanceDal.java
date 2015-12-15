package com.dianping.paas.core.dal;

import com.dianping.paas.core.dal.dao.InstanceDao;
import com.dianping.paas.core.dal.entity.InstanceEntity;
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
    public List<InstanceEntity> getRunningInstances(String app_id, String app_version) {
        return null;
    }
}
