package com.dianping.paas.proc.common;

import java.io.File;
import java.io.IOException;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2016/02/02 11:41.
 */
public interface CgroupFileMapping {

    /**
     * 将cgroupfile的文件路径映射成写的load的文件路径
     * example /cgroup/docker/1234567 -> /data/cgroup/docker/1234567/loadavg
     * 其中,将 /data/cgroup/docker/ 搞成tempfs(内存文件系统)可以加快读写速度
     *
     * @param cgroupFile
     * @return
     * @throws IOException
     */
    File toLoadAvgFile(File cgroupFile) throws IOException;
}
