package com.dianping.paas.core.dto.request;

import lombok.Data;

/**
 * Created by yuchao on 12/3/15.
 */
@Data
public class AllocateWebPackageRequest {

    private String md5;

    private String appId;

    private String appVersion;

}
