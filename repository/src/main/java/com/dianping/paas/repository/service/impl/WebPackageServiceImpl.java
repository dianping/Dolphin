package com.dianping.paas.repository.service.impl;

import com.dianping.paas.core.config.ConfigManager;
import com.dianping.paas.core.dal.AppFileDal;
import com.dianping.paas.core.dal.AppVersionDal;
import com.dianping.paas.core.dal.entity.AppFileEntity;
import com.dianping.paas.core.dal.entity.AppVersionEntity;
import com.dianping.paas.core.dto.request.AllocateWebPackageRequest;
import com.dianping.paas.core.dto.request.DownloadWebPackageRequest;
import com.dianping.paas.core.dto.request.UploadWebPackageRequest;
import com.dianping.paas.core.dto.response.AllocateWebPackageResponse;
import com.dianping.paas.core.dto.response.DownloadWebPackageResponse;
import com.dianping.paas.core.dto.response.UploadWebPackageResponse;
import com.dianping.paas.repository.enums.PkgBackUpStatus;
import com.dianping.paas.repository.service.WebPackageService;
import com.dianping.paas.repository.util.WebPackageUtil;
import com.google.common.io.Files;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * Created by yuchao on 12/3/15.
 */
@Component
public class WebPackageServiceImpl implements WebPackageService {

    private static final Logger logger = LogManager.getLogger(WebPackageServiceImpl.class);

    @Resource
    private AppFileDal appFileDal;

    @Resource
    private AppVersionDal appVersionDal;

    @Resource
    private ConfigManager configManager;

    public AllocateWebPackageResponse allocate(AllocateWebPackageRequest request) {

        AllocateWebPackageResponse response = new AllocateWebPackageResponse();

        try {
            logger.info(String.format("begin allocate WebPackage: %s", request));

            AppFileEntity appFile = allocateAppFile(request);
            appFileDal.insert(appFile);

            String repositoryUploadUrl = WebPackageUtil.generateUploadUrl(appFile.getToken());
            response.setUploadUrl(repositoryUploadUrl);
            response.success();
        } catch (Exception e) {
            response.fail(e.toString());
            logger.error(String.format("error when allocate WebPackage: %s", request), e);
        }

        logger.info(String.format("end allocate WebPackage: %s", response));

        return response;
    }

    private AppFileEntity allocateAppFile(AllocateWebPackageRequest req) throws IOException {
        AppFileEntity appFile = new AppFileEntity();
        String token = UUID.randomUUID().toString();

        appFile.setAppId(req.getAppId());
        appFile.setAppVersion(req.getAppVersion());
        appFile.setLocalPath(new File(configManager.getLocalRepositoryBaseDir(), token).getCanonicalPath());
        appFile.setMd5(req.getMd5());
        appFile.setToken(token);
        appFile.setDownloadUrl(configManager.getRepositoryDownloadUrl(token));
        appFile.setUploadUrl(configManager.getRepositoryUploadUrl(token));
        appFile.setBackupStatus(PkgBackUpStatus.NOT_BACKUPED.getCode());
        appFile.setCreationDate(new Date());

        return appFile;
    }

    public UploadWebPackageResponse upload(UploadWebPackageRequest request) {
        logger.info(String.format("begin upload WebPackage: %s", request));

        UploadWebPackageResponse response = new UploadWebPackageResponse();

        File webPackageFile = WebPackageUtil.newWebPackageFile(request.getToken());
        try {
            Files.write(request.getFile().getBytes(), webPackageFile);
            response.success();
            saveOrUpdateAppVersion(request.getToken());
        } catch (IOException e) {
            response.fail(e.toString());
            logger.error(String.format("error when upload: %s", request), e);
        }

        logger.info(String.format("end upload WebPackage: %s", response));

        return response;
    }

    private void saveOrUpdateAppVersion(String token) {
        AppFileEntity appFile = appFileDal.findAppFileByToken(token);

        AppVersionEntity appVersion = new AppVersionEntity();
        appVersion.setAppId(appFile.getAppId());
        appVersion.setVersion(appFile.getAppVersion());
        appVersion.setAppFileId(appFile.getId());

        AppVersionEntity oldVersion = appVersionDal.findByAppIdAndVersion(appFile.getAppId(), appFile.getAppVersion());

        if (oldVersion == null) {
            appVersion.setCreationDate(new Date());
            appVersion.setLastModifiedDate(new Date());
            appVersionDal.insert(appVersion);
        } else {
            appVersion.setId(oldVersion.getId());
            appVersion.setLastModifiedDate(new Date());
            appVersionDal.update(appVersion);
        }
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
