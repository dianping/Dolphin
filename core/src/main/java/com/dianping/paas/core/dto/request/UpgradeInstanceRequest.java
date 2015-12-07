package com.dianping.paas.core.dto.request;

import lombok.Data;

/**
 * Created by yuchao on 12/7/15.
 */
@Data
public class UpgradeInstanceRequest {
    private String app_id;
    private String app_version;
    private String webPackageUrl;
    private String instance_id;
}
