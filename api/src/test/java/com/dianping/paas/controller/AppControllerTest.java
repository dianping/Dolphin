package com.dianping.paas.controller;

import com.dianping.paas.entity.AppEntity;
import com.dianping.paas.test.ControllerBaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 15/11/2.
 */
public class AppControllerTest extends ControllerBaseTest {

    @Test
    public void testGetAll() throws Exception {
        List<AppEntity> appEntityList = getResponseAsBean("/v1/apps");
        Assert.assertTrue(appEntityList.size() > 0);
    }
}