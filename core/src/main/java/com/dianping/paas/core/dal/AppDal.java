package com.dianping.paas.core.dal;

import com.dianping.paas.core.dal.dao.AppDao;
import com.dianping.paas.core.dal.entity.AppEntity;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/15 14:21.
 */
@Repository
public class AppDal {
    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private AppDao appDao;

    public List<AppEntity> getAll() {
        return appDao.findAll();
    }
}
