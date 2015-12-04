package com.dianping.paas.core.dto.request;

import lombok.Data;

/**
 * Created by yuchao on 12/4/15.
 */
@Data
public class InstanceRestartRequest {
    private String containerId;
}
