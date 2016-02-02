package com.dianping.paas.proc.util;

import java.io.File;
import java.io.IOException;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2016/02/02 14:42.
 */
public class IO {
    public static void createFileIfNotExist(File file) throws IOException {
        File parentDir = file.getParentFile();

        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }
}
