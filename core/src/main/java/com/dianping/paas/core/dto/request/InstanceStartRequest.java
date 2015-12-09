package com.dianping.paas.core.dto.request;

import lombok.Data;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/02 14:31.
 */
@Data
public class InstanceStartRequest {
    private String repository;
    private String appName;
    private String imageId;
    private int instanceIndex;
}
