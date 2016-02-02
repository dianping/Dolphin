package com.dianping.paas.proc.common;

import com.dianping.paas.proc.enums.ProcStatus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2016/02/01 15:08.
 */
public interface ProcReader {
    /**
     * 列出所有容器对应的cgroup根目录
     *
     * @return cgroup列表
     */
    List<File> listContainerCgroupFiles() throws FileNotFoundException;


    /**
     * 列出cgroup下对应的所有的进程
     *
     * @param cgroupFile
     * @return
     */
    List<String> listProcesses(File cgroupFile) throws IOException;

    /**
     * 列出某个进程所有的task状态
     *
     * @return
     * @param pid
     */
    Map<String, ProcStatus> listTaskStatus(String pid) throws IOException;
}
