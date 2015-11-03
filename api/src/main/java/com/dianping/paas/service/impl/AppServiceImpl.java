package com.dianping.paas.service.impl;

import com.dianping.paas.dao.AppDao;
import com.dianping.paas.entity.AppEntity;
import com.dianping.paas.service.AppService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 15/11/3.
 */
@Service
public class AppServiceImpl implements AppService {
    @Resource
    private AppDao appDao;

    public List<AppEntity> getAll() {
        return appDao.findAll();
    }
}
