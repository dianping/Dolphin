package com.dianping.paas.util;

import com.dianping.paas.config.ConfigManager;
import com.dianping.paas.extension.ExtensionLoader;
import com.google.common.io.Files;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.UUID;

/**
 * Created by yuchao on 11/23/15.
 */
public class TempFileGenerator {
    private static final Logger logger = LogManager.getLogger(TempFileGenerator.class);

    private static final ConfigManager configManager = ExtensionLoader.getExtension(ConfigManager.class);

    public static File generate(String fileContent) {
        File file = null;

        try {
            String tempDir = getTempDir();
            file = new File(tempDir, getRandomFileName());
            Files.write(fileContent, file, Charset.defaultCharset());
        } catch (IOException e) {
            logger.error("generate temp file failed!", e);
        }

        return file;

    }

    private static String getTempDir() throws IOException {
        String tempDir = configManager.getTempDir();
        File tempDirFile = new File(tempDir);
        if (!tempDirFile.exists()) {
            tempDirFile.mkdir();
        }

        return tempDir;
    }

    private static String getRandomFileName() {
        return UUID.randomUUID().toString();
    }
}
