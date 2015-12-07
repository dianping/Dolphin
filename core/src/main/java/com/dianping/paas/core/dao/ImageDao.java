package com.dianping.paas.core.dao;

import com.dianping.paas.core.entity.ImageEntity;

/**
 * Created by yuchao on 12/1/15.
 */
public interface ImageDao {
    ImageEntity getByImageType(String image_type);
}
