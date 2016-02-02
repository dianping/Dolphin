package com.dianping.paas.proc.load;

import com.dianping.paas.proc.common.CgroupFileMapping;
import com.dianping.paas.proc.common.ProcReader;
import com.dianping.paas.proc.common.Processor;
import com.dianping.paas.proc.enums.ProcStatus;
import com.dianping.paas.proc.load.linux.LoadCalculator;
import com.google.common.io.Files;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2016/02/02 10:17.
 */
@Component
public class LoadProcessor implements Processor {

    @Resource
    private ProcReader procReader;

    @Setter
    @Resource
    private CgroupFileMapping cgroupFileMapping;

    public static Map<String, Load> cgroupLoadMap = new HashMap<String, Load>();

    @Override
    public void execute() throws Exception {
        List<File> cgroupFiles = procReader.listContainerCgroupFiles();
        for (File cgroupFile : cgroupFiles) {
            calcLoadAndWriteToFile(cgroupFile);
        }
    }

    private void calcLoadAndWriteToFile(File cgroupFile) throws IOException {
        // 得到上次的Load
        Load load = getLastLoad(cgroupFile);

        // 得到taskInfo
        TaskInfo taskInfo = getTaskInfo(cgroupFile);

        // 计算本次Load
        String loadStr = LoadCalculator.calcLoad(load, taskInfo);

        // 将load写入到文件
        writeLoadStrToFile(cgroupFile, loadStr);
    }


    private TaskInfo getTaskInfo(File cgroupFile) throws IOException {
        TaskInfo taskInfo = new TaskInfo();

        int runningTaskCount = 0;
        int totalTaskCount = 0;

        List<String> processes = procReader.listProcesses(cgroupFile);
        for (String process : processes) {
            Map<String, ProcStatus> taskStatusMap = procReader.listTaskStatus(process);
            runningTaskCount += getRunningTaskCount(taskStatusMap);
            totalTaskCount += taskStatusMap.size();
        }

        taskInfo.setRunningTaskCount(runningTaskCount);
        taskInfo.setTotalTaskCount(totalTaskCount);

        return taskInfo;
    }

    private Load getLastLoad(File cgroupFile) {
        Load load = cgroupLoadMap.get(cgroupFile.getAbsolutePath());
        if (load == null) {
            load = new Load();
            cgroupLoadMap.put(cgroupFile.getAbsolutePath(), load);
        }

        return load;
    }

    private void writeLoadStrToFile(File cgroupFile, String loadStr) throws IOException {
        File loadAvgFile = cgroupFileMapping.toLoadAvgFile(cgroupFile);

        Files.write(loadStr, loadAvgFile, Charset.defaultCharset());
    }

    private int getRunningTaskCount(Map<String, ProcStatus> taskStatusMap) {
        int runningTaskCount = 0;

        for (ProcStatus procStatus : taskStatusMap.values()) {
            if (procStatus.equals(ProcStatus.RUNNING)) {
                ++runningTaskCount;
            }
        }

        return runningTaskCount;
    }
}
