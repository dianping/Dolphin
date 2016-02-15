package com.dianping.paas.core.dal;

import com.dianping.paas.core.dal.dao.AppPlanDao;
import com.dianping.paas.core.dal.entity.AppPlanEntity;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 15/12/15.
 */
@Repository
public class AppPlanDal {

    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private AppPlanDao appPlanDao;

    public AppPlanEntity findByPK(long id){
        return appPlanDao.findByPK(id);
    }


}
