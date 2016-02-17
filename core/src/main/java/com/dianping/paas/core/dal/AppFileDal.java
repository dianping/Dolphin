package com.dianping.paas.core.dal;

import com.dianping.paas.core.dal.dao.AppFileDao;
import com.dianping.paas.core.dal.entity.AppFileEntity;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 16/2/16.
 */
@Repository
public class AppFileDal {

    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private AppFileDao appFileDao;

    public long insert(AppFileEntity appFile){
        return appFileDao.insert(appFile);
    }

    public AppFileEntity findAppFileByToken(String token) {
        return appFileDao.findAppFileByToken(token);
    }
}
