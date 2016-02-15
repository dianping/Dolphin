package com.dianping.paas.core.dto.response;

import lombok.Data;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 16/2/15.
 */
@Data
public class OperationInfo {

    private long operationId;

    private String appId;

    private String appVersion;

    private String token;

}
