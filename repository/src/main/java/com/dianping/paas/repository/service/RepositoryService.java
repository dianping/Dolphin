package com.dianping.paas.repository.service;

import com.dianping.paas.core.dto.request.AllocateWebPackageRequest;
import com.dianping.paas.core.dto.request.DownloadWebPackageRequest;
import com.dianping.paas.core.dto.request.UploadWebPackageRequest;
import com.dianping.paas.core.dto.response.AllocateWebPackageResponse;
import com.dianping.paas.core.dto.response.UploadWebPackageResponse;

/**
 * Created by yuchao on 12/3/15.
 */
public interface RepositoryService {
    AllocateWebPackageResponse allocateWebPackageUploadUrl(AllocateWebPackageRequest request);

    UploadWebPackageResponse uploadWebPackage(UploadWebPackageRequest request);

    UploadWebPackageResponse downloadWebPackage(DownloadWebPackageRequest request);
}
