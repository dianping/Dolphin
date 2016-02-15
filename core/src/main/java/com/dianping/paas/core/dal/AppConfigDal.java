package com.dianping.paas.core.dal;

import com.dianping.paas.core.dal.dao.AppConfigDao;
import com.dianping.paas.core.dal.entity.AppConfigEntity;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 15/12/15.
 */
@Repository
public class AppConfigDal {

    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private AppConfigDao appConfigDao;

    public long insert(AppConfigEntity appConfig) {
        return appConfigDao.insert(appConfig);
    }

    public AppConfigEntity findByAppId(String appId){
        return appConfigDao.findByAppId(appId);
    }
}
