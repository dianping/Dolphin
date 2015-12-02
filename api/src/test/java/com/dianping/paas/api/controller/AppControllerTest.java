package com.dianping.paas.api.controller;

import com.dianping.paas.core.entity.AppEntity;
import com.dianping.paas.test.ControllerBaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 15/11/2.
 */
public class AppControllerTest extends ControllerBaseTest {

    private static final Logger logger = LogManager.getLogger(AppControllerTest.class);

    @Test
    public void testGetAll() throws Exception {
        logger.info("start test getAll..");
        List<AppEntity> appEntityList = getResponseAsBean("/v1/apps");
        Assert.assertTrue(appEntityList.size() > 0);
        logger.info("end test getAll!");
    }
}