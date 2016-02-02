package com.dianping.paas.proc.load;

import com.dianping.paas.proc.BaseTest;
import com.dianping.paas.proc.common.CgroupFileMapping;
import com.dianping.paas.proc.util.IO;
import com.google.common.io.Files;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2016/02/02 11:49.
 */

public class LoadProcessorTest extends BaseTest {

    @Resource
    private LoadProcessor loadProcessor;

    @Before
    public void setUp() throws Exception {
        loadProcessor.setCgroupFileMapping(new CgroupFileMapping() {
            @Override
            public File toLoadAvgFile(File cgroupFile) throws IOException {
                File file = new File(ROOT_PATH, String.format("/proc/%s/loadavg", cgroupFile.getName()));
                IO.createFileIfNotExist(file);
                return file;
            }
        });

    }

    @Test
    public void testExecute() throws Exception {
        for (int i = 0; i < 1000; i++) {
            loadProcessor.execute();
        }

        String loadAvg = Files.readFirstLine(new File(ROOT_PATH, "/proc/must_contains_2_running_tasks_to_paas_unit_test22222222222222222/loadavg"), Charset.defaultCharset());

        Assert.assertTrue(loadAvg.startsWith("2.00"));

        loadAvg = Files.readFirstLine(new File(ROOT_PATH, "/proc/must_contains_5_running_tasks_to_paas_unit_test55555555555555555/loadavg"), Charset.defaultCharset());

        Assert.assertTrue(loadAvg.startsWith("5.00"));
    }
}