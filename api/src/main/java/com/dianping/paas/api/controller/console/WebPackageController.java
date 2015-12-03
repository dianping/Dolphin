package com.dianping.paas.api.controller.console;

import com.dianping.paas.core.dto.request.AllocateWebPackageRequest;
import com.dianping.paas.core.dto.request.DownloadWebPackageRequest;
import com.dianping.paas.core.dto.request.UploadWebPackageRequest;
import com.dianping.paas.core.dto.response.AllocateWebPackageResponse;
import com.dianping.paas.core.dto.response.UploadWebPackageResponse;
import com.dianping.paas.core.service.AppService;
import com.dianping.paas.repository.service.RepositoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by yuchao on 12/3/15.
 */
@RestController
@RequestMapping("/v1/repositories/webpackages")
public class WebPackageController {

    @Resource
    private AppService appService;

    @Resource
    private RepositoryService repositoryService;


    /**
     * 分配 web package 的上传地址的时候 需要向repository发消息
     */
    @RequestMapping(value = "/allocations", method = RequestMethod.POST)
    public AllocateWebPackageResponse allocateWebPackage(AllocateWebPackageRequest request) {

        return appService.allocateWebPackage(request);
    }

    /**
     * 上传 web package 的时候已经落在了本机,所以直接执行,不用nats来发消息
     */
    @RequestMapping(value = "/uploads", method = RequestMethod.POST)
    public UploadWebPackageResponse uploadWebPackage(MultipartFile file, UploadWebPackageRequest request) throws IOException {
        request.setBytes(file.getBytes());

        return repositoryService.uploadWebPackage(request);
    }

    /**
     * 下载 web package 的时候已经落在了本机,所以直接执行,不用nats来发消息
     */
    @RequestMapping(value = "/downloads", method = RequestMethod.POST)
    public UploadWebPackageResponse downloadWebPackage(DownloadWebPackageRequest request) {

        return repositoryService.downloadWebPackage(request);
    }
}
