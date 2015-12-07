package com.dianping.paas.core.util;

import com.google.common.io.ByteStreams;
import com.google.common.io.Closeables;
import com.google.common.io.Files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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


    public static void unZip(ZipInputStream zis, String baseDirLocation) throws IOException {
        File baseDir = createOrGetDir(baseDirLocation);

        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            if (entry.isDirectory()) {

                new File(baseDir, entry.getName()).mkdirs();

            } else {
                File target = new File(baseDir, entry.getName());

                target.getParentFile().mkdirs();

                FileOutputStream outputStream = new FileOutputStream(target);
                ByteStreams.copy(zis, outputStream);

                Closeables.close(outputStream, true);
            }
        }
        Closeables.close(zis, true);
    }
}
