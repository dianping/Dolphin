package com.dianping.paas.repository.service.impl;

import com.dianping.paas.core.config.ConfigManager;
import com.dianping.paas.core.dto.request.AllocateWebPackageRequest;
import com.dianping.paas.core.dto.request.DownloadWebPackageRequest;
import com.dianping.paas.core.dto.request.UploadWebPackageRequest;
import com.dianping.paas.core.dto.response.AllocateWebPackageResponse;
import com.dianping.paas.core.dto.response.UploadWebPackageResponse;
import com.dianping.paas.core.extension.ExtensionLoader;
import com.dianping.paas.repository.service.RepositoryService;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by yuchao on 12/3/15.
 */
@Component
public class RepositoryServiceImpl implements RepositoryService {
    private ConfigManager configManager = ExtensionLoader.getExtension(ConfigManager.class);


    public AllocateWebPackageResponse allocateWebPackage(AllocateWebPackageRequest request) {
        AllocateWebPackageResponse response = new AllocateWebPackageResponse();

        String token = UUID.randomUUID().toString();
        String repositoryUploadUrl = configManager.getRepositoryUploadUrl(token);
        response.setUploadUrl(repositoryUploadUrl);

        return response;
    }

    public UploadWebPackageResponse uploadWebPackage(UploadWebPackageRequest request) {
        return null;
    }

    public UploadWebPackageResponse downloadWebPackage(DownloadWebPackageRequest request) {
        return null;
    }
}
