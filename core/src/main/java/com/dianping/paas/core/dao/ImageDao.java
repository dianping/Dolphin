package com.dianping.paas.core.dao;

import com.dianping.paas.core.dao.support.CrudDao;
import com.dianping.paas.core.entity.ImageEntity;

/**
 * Created by yuchao on 12/1/15.
 */
public interface ImageDao extends CrudDao<ImageEntity>{
    ImageEntity getByImageType(String image_type);
}
