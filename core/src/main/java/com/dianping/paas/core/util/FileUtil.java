package com.dianping.paas.core.util;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/02 11:00.
 */
public class FileUtil {

    /**
     * 指定内容写到指定文件
     *
     * @param location 文件路径
     * @param content  文件内容
     * @throws IOException
     */
    public static File write(String location, String content) throws IOException {
        File file = new File(location);

        file.getParentFile().mkdirs();
        Files.write(content, file, Charset.defaultCharset());

        return file;
    }

    /**
     * 创建或者获取某个目录
     *
     * @param location 目录路径
     * @return
     */
    public static File createOrGetDir(String location) {
        File file = new File(location);

        if (!file.exists()) {
            file.mkdirs();
        }

        return file;
    }
}
