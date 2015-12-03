package com.dianping.paas.core.dto.request;

import lombok.Data;

/**
 * Created by yuchao on 12/3/15.
 */
@Data
public class UploadWebPackageRequest {
    /**
     * 上传的文件字节
     */
    private byte[] bytes;
    /**
     * 用于获取upload url的标识
     */
    private String token;
}
