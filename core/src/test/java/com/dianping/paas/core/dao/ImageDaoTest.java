package com.dianping.paas.core.dao;

import com.dianping.paas.core.entity.ImageEntity;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Created by yuchao on 12/1/15.
 */
public class ImageDaoTest extends CrudDaoTest {
    @Resource
    private ImageDao imageDao;

    private ImageEntity imageEntity;

    @Override
    protected void prepareData() {
        imageEntity = new ImageEntity();
        imageEntity.setImage_type(UUID.randomUUID().toString());
        imageEntity.setDockerfile_template("From busybox:latest");

        setMetadata(imageDao, imageEntity);
    }

    @Override
    protected void doUpdate() {
        imageEntity.setImage_type("tomcat-updated");
    }

    @Test
    public void testGetByImageType() throws Exception {
        ImageEntity savedAppEntity = imageDao.getByImageType(imageEntity.getImage_type());
        Assert.assertEquals(imageEntity.getImage_type(), savedAppEntity.getImage_type());
    }
}