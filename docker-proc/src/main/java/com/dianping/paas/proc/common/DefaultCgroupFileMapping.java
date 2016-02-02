package com.dianping.paas.proc.common;

import com.dianping.paas.proc.util.IO;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2016/02/02 11:09.
 */
@Component
public class DefaultCgroupFileMapping implements CgroupFileMapping {

    @Override
    public File toLoadAvgFile(File cgroupFile) throws IOException {
        File loadAvgFile = new File(String.format("/data/%s/loadavg", cgroupFile.getAbsoluteFile()));

        IO.createFileIfNotExist(loadAvgFile);

        return loadAvgFile;
    }
}
