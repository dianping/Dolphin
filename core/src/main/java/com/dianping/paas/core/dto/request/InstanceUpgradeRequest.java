package com.dianping.paas.core.dto.request;

import lombok.Data;

/**
 * Created by yuchao on 12/7/15.
 */
@Data
public class InstanceUpgradeRequest {
    private String app_id;

    private String app_version;

    private String webPackageUrl;

    private String instance_id;

    private long instance_index;

    private String agent_ip;
}
