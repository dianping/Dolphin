package com.dianping.paas.core.util;

import org.junit.Test;
import org.springframework.util.Assert;

import java.io.File;

/**
 * Created by yuchao on 11/23/15.
 */
public class TempFileGeneratorTest {

    @Test
    public void testGenerate() throws Exception {
        File file = TempFileGenerator.generate("xxx");
        Assert.notNull(file);
    }
}