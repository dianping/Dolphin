package com.dianping.paas.core.dal;

import com.dianping.paas.core.dal.dao.AppVersionDao;
import com.dianping.paas.core.dal.entity.AppVersionEntity;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 16/2/17.
 */
@Repository
public class AppVersionDal {
    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private AppVersionDao appVersionDao;

    public AppVersionEntity findByAppIdAndVersion(String appId, String appVersion) {
        return appVersionDao.findByAppIdAndVersion(appId, appVersion);
    }

    public long insert(AppVersionEntity appVersion) {
        return appVersionDao.insert(appVersion);
    }

    public int update(AppVersionEntity appVersion) {
        return appVersionDao.update(appVersion);
    }
}
