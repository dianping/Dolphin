package com.dianping.paas.proc.common;

import com.dianping.paas.proc.enums.ProcStatus;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import lombok.Setter;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2016/02/01 15:26.
 */
public class DefaultProcReader implements ProcReader {

    public static final String CGROUP_PROCS = "cgroup.procs";

    public static final String STATUS = "status";

    @Setter
    private String cgroupBasePath = "/cgroup";
    @Setter
    private String procBasePath = "/proc";

    @Setter
    private Integer containerNameLength = 64;

    @Override
    public List<File> listContainerCgroupFiles() throws FileNotFoundException {

        File file = new File(cgroupBasePath);
        if (!file.exists()) {
            throw new FileNotFoundException(cgroupBasePath + " is not exist!");
        }

        return Arrays.asList(file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().length() == containerNameLength;
            }
        }));

    }

    @Override
    public List<String> listProcesses(File cgroupFile) throws IOException {
        File cgroupProcsFile = new File(cgroupFile, CGROUP_PROCS);
        if (!cgroupProcsFile.exists()) {
            throw new FileNotFoundException(cgroupProcsFile.getAbsolutePath() + "is not exist!");
        }

        return Files.readLines(cgroupProcsFile, Charset.defaultCharset());
    }

    @Override
    public Map<String, ProcStatus> listTaskStatus(String pid) throws IOException {
        File taskDir = new File(String.format("%s/%s/task", procBasePath, pid));
        Map<String, ProcStatus> procStatusMap = Maps.newHashMap();

        for (File pidDir : taskDir.listFiles()) {
            File statusFile = new File(pidDir, STATUS);
            List<String> lines = Files.readLines(statusFile, Charset.defaultCharset());
            String statusStr = lines.get(1);
            procStatusMap.put(pidDir.getName(), ProcStatus.convertStatus(statusStr));
        }

        return procStatusMap;
    }
}
