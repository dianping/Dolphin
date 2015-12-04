package com.dianping.paas.repository.util;

import com.dianping.paas.core.config.ConfigManager;
import com.dianping.paas.core.extension.ExtensionLoader;
import com.dianping.paas.core.util.FileUtil;

import java.io.File;
import java.util.UUID;

/**
 * Created by yuchao on 12/4/15.
 */
public class WebPackageUtil {
    private static ConfigManager configManager = ExtensionLoader.getExtension(ConfigManager.class);


    public static File newWebPackageFile(String token) {
        File webPackageDir = FileUtil.createOrGetDir(configManager.getWebPackageBaseDir());

        return new File(webPackageDir, token);
    }


    public static String generateUploadUrl() {

        return configManager.getRepositoryUploadUrl(newToken());
    }

    public static String newToken() {

        return UUID.randomUUID().toString();
    }
}
