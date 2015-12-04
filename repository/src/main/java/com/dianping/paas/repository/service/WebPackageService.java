package com.dianping.paas.repository.service;

import com.dianping.paas.core.dto.request.AllocateWebPackageRequest;
import com.dianping.paas.core.dto.request.DownloadWebPackageRequest;
import com.dianping.paas.core.dto.request.UploadWebPackageRequest;
import com.dianping.paas.core.dto.response.AllocateWebPackageResponse;
import com.dianping.paas.core.dto.response.DownloadWebPackageResponse;
import com.dianping.paas.core.dto.response.UploadWebPackageResponse;

/**
 * Created by yuchao on 12/3/15.
 */
public interface WebPackageService {
    AllocateWebPackageResponse allocate(AllocateWebPackageRequest request);

    UploadWebPackageResponse upload(UploadWebPackageRequest request);

    DownloadWebPackageResponse download(DownloadWebPackageRequest request);
}
