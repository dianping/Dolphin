package com.dianping.paas.core.dao.impl;

import com.dianping.paas.core.dao.ImageDao;
import com.dianping.paas.core.dao.support.DefaultCrudDao;
import com.dianping.paas.core.entity.ImageEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by yuchao on 12/1/15.
 */
@Repository
public class ImageDaoImpl extends DefaultCrudDao<ImageEntity> implements ImageDao {

    public ImageEntity getByImageType(String image_type) {
        String statementName = getNamespace() + "." + "getByImageType";
        return getGenericDaoSupport().get(statementName, image_type);
    }

    public String getNamespace() {
        return ImageEntity.class.getCanonicalName();
    }
}
