package com.dianping.paas.core.dal.dao;

import com.dianping.paas.core.dal.entity.ImageEntity;

/**
 * Created by yuchao on 12/1/15.
 */
public interface ImageDao {
    ImageEntity getByImageType(String image_type);
}
