package com.dianping.paas.core.dal;

import com.dianping.paas.core.dal.dao.QuotaDao;
import com.dianping.paas.core.dal.entity.QuotaEntity;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 15/12/15.
 */
@Repository
public class QuotaDal {

    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private QuotaDao quotaDao;

    public long insert(QuotaEntity quota) {
        return quotaDao.insert(quota);
    }
}
