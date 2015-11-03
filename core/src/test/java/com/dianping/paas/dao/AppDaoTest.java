package com.dianping.paas.dao;

import com.dianping.paas.entity.AppEntity;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;


/**
 * Created by yuchao on 15/11/2.
 */
public class AppDaoTest extends CrudDaoTest {

    @Resource
    private AppDao appDao;

    private AppEntity appEntity;

    @Override
    protected void prepareData() {
        appEntity = new AppEntity();
        appEntity.setApp_id("val_app_id");
        appEntity.setType("val_type");
        appEntity.setOwner("val_owner");
        appEntity.setQuota_id((long) 1);
        appEntity.setLevel(1);
        appEntity.setApp_plan_name("val_app_plan_name");
        appEntity.setCreation_date(new Date());
        appEntity.setLast_modified_date(new Date());
        appEntity.setImage("val_image");
        appEntity.setMachine_label("val_machine_label");

        setMetadata(appDao, appEntity);
    }

    @Override
    protected void doUpdate() {
        appEntity.setApp_id("app_id_updated");
    }

    @Test
    public void testGetByAppId() throws Exception {
        AppEntity savedAppEntity = appDao.getByAppId(appEntity.getApp_id());
        Assert.assertEquals(appEntity.getApp_id(), savedAppEntity.getApp_id());
    }
}