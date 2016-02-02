package com.dianping.paas.proc;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2016/02/02 14:25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath*:spring.xml"})
public abstract class BaseTest {
    public static final String ROOT_PATH = BaseTest.class.getClassLoader().getResource("//").getPath();
}
