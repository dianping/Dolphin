package com.dianping.paas.proc.common;

import com.dianping.paas.proc.BaseTest;
import com.dianping.paas.proc.enums.ProcStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.File;
import java.util.Map;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2016/02/01 15:35.
 */
public class DefaultProcReaderTest extends BaseTest {

    @Resource
    private ProcReader procReader;
    private String cgroupBasePath;
    private String procBasePath;

    @Before
    public void setUp() throws Exception {
        cgroupBasePath = new File(new File(ROOT_PATH), "/cgroup/docker").getAbsolutePath();
        procBasePath = new File(new File(ROOT_PATH), "/proc").getAbsolutePath();

        ((DefaultProcReader) procReader).setCgroupBasePath(cgroupBasePath);
        ((DefaultProcReader) procReader).setProcBasePath(procBasePath);
    }

    @Test
    public void testListCgroupFile() throws Exception {
        Assert.assertTrue(procReader.listContainerCgroupFiles().size() == 2);
    }

    @Test
    public void testListProcesses() throws Exception {
        File parent = new File(cgroupBasePath);
        File cgroupFile1 = new File(parent, "must_contains_2_running_tasks_to_paas_unit_test22222222222222222");
        File cgroupFile2 = new File(parent, "must_contains_5_running_tasks_to_paas_unit_test55555555555555555");

        Assert.assertEquals(procReader.listProcesses(cgroupFile1).size(), 2);
        Assert.assertEquals(procReader.listProcesses(cgroupFile2).size(), 3);

    }

    @Test
    public void testListTaskStatus() throws Exception {
        Map<String, ProcStatus> procStatusMap = procReader.listTaskStatus("1001");

        Assert.assertEquals(procStatusMap.get("1001001"), ProcStatus.RUNNING);
        Assert.assertEquals(procStatusMap.get("1001002"), ProcStatus.SLEEPING);

        procStatusMap = procReader.listTaskStatus("1002");
        Assert.assertEquals(procStatusMap.get("1002001"), ProcStatus.RUNNING);
        Assert.assertEquals(procStatusMap.get("1002002"), ProcStatus.ZOMBIE);
    }
}