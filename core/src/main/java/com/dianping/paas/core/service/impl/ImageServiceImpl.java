package com.dianping.paas.core.service.impl;

import com.dianping.paas.core.dao.ImageDao;
import com.dianping.paas.core.entity.ImageEntity;
import com.dianping.paas.core.service.ImageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by yuchao on 12/1/15.
 */
@Service
public class ImageServiceImpl implements ImageService {
    @Resource
    private ImageDao imageDao;


    public String getDockerfileTemplateContent(String image_type) {
        ImageEntity imageEntity = imageDao.getByImageType(image_type);

        return imageEntity.getDockerfile_template();
    }
}
