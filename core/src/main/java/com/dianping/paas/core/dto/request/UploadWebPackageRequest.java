package com.dianping.paas.core.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by yuchao on 12/3/15.
 */
@Data
public class UploadWebPackageRequest {
    private MultipartFile file;
    /**
     * 用于获取upload url的标识
     */
    private String token;
}
