package com.dianping.paas.core.dal.dao;

import com.dianping.paas.core.dal.entity.AppVersionEntity;
import org.apache.ibatis.annotations.Param;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 16/2/17.
 */
public interface AppVersionDao {

    AppVersionEntity findByAppIdAndVersion(@Param("appId") String appId, @Param("appVersion") String appVersion);

    long insert(AppVersionEntity appVersion);

    int update(AppVersionEntity appVersion);
}
