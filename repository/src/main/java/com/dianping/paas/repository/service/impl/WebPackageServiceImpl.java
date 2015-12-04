package com.dianping.paas.repository.service.impl;

import com.dianping.paas.core.dto.request.AllocateWebPackageRequest;
import com.dianping.paas.core.dto.request.DownloadWebPackageRequest;
import com.dianping.paas.core.dto.request.UploadWebPackageRequest;
import com.dianping.paas.core.dto.response.AllocateWebPackageResponse;
import com.dianping.paas.core.dto.response.DownloadWebPackageResponse;
import com.dianping.paas.core.dto.response.UploadWebPackageResponse;
import com.dianping.paas.repository.service.WebPackageService;
import com.dianping.paas.repository.util.WebPackageUtil;
import com.google.common.io.Files;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Created by yuchao on 12/3/15.
 */
@Component
public class WebPackageServiceImpl implements WebPackageService {
    private static final Logger logger = LogManager.getLogger(WebPackageServiceImpl.class);

    public AllocateWebPackageResponse allocate(AllocateWebPackageRequest request) {
        logger.info(String.format("begin allocate WebPackage: %s", request));

        AllocateWebPackageResponse response = new AllocateWebPackageResponse();

        String repositoryUploadUrl = WebPackageUtil.generateUploadUrl();
        response.setUploadUrl(repositoryUploadUrl);
        response.success();

        logger.info(String.format("end allocate WebPackage: %s", response));

        return response;
    }

    public UploadWebPackageResponse upload(UploadWebPackageRequest request) {
        logger.info(String.format("begin upload WebPackage: %s", request));

        UploadWebPackageResponse response = new UploadWebPackageResponse();

        File webPackageFile = WebPackageUtil.newWebPackageFile(request.getToken());
        try {
            Files.write(request.getFile().getBytes(), webPackageFile);
            response.success();
        } catch (IOException e) {
            response.fail(e.toString());
            logger.error(String.format("error when upload: %s", request), e);
        }

        logger.info(String.format("end upload WebPackage: %s", response));

        return response;
    }


    public DownloadWebPackageResponse download(DownloadWebPackageRequest request) {
        logger.info(String.format("begin download WebPackage: %s", request));

        DownloadWebPackageResponse response = new DownloadWebPackageResponse();

        File webPackageFile = WebPackageUtil.newWebPackageFile(request.getToken());
        response.setFileSystemResource(new FileSystemResource(webPackageFile));
        response.success();

        logger.info(String.format("end download WebPackage: %s", response));

        return response;
    }
}
