package com.dianping.paas.repository.service.impl;

import com.dianping.paas.core.config.ConfigManager;
import com.dianping.paas.core.dto.request.AllocateWebPackageRequest;
import com.dianping.paas.core.dto.request.DownloadWebPackageRequest;
import com.dianping.paas.core.dto.request.UploadWebPackageRequest;
import com.dianping.paas.core.dto.response.AllocateWebPackageResponse;
import com.dianping.paas.core.dto.response.UploadWebPackageResponse;
import com.dianping.paas.core.extension.ExtensionLoader;
import com.dianping.paas.core.util.FileUtil;
import com.dianping.paas.repository.service.RepositoryService;
import com.google.common.io.Files;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by yuchao on 12/3/15.
 */
@Component
public class RepositoryServiceImpl implements RepositoryService {
    public static final Logger logger = LogManager.getLogger(RepositoryServiceImpl.class);

    private ConfigManager configManager = ExtensionLoader.getExtension(ConfigManager.class);


    public AllocateWebPackageResponse allocateWebPackage(AllocateWebPackageRequest request) {
        AllocateWebPackageResponse response = new AllocateWebPackageResponse();

        String token = UUID.randomUUID().toString();
        String repositoryUploadUrl = configManager.getRepositoryUploadUrl(token);
        response.setUploadUrl(repositoryUploadUrl);

        return response;
    }

    public UploadWebPackageResponse uploadWebPackage(UploadWebPackageRequest request) {
        UploadWebPackageResponse response = new UploadWebPackageResponse();

        File webPackageFile = buildWebPackageFile(request.getToken());

        try {
            Files.write(request.getBytes(), webPackageFile);
            response.success();
        } catch (IOException e) {
            logger.error(String.format("error when uploadWebPackage: %s", request), e);
            response.fail(e.toString());
        }

        return response;
    }

    private File buildWebPackageFile(String token) {
        File webPackageDir = FileUtil.createOrGetDir(configManager.getWebPackageBaseDir());

        return new File(webPackageDir, token);
    }

    public UploadWebPackageResponse downloadWebPackage(DownloadWebPackageRequest request) {
        return null;
    }
}
