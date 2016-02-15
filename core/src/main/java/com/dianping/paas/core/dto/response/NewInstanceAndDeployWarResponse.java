package com.dianping.paas.core.dto.response;

import lombok.Data;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 16/2/15.
 */
@Data
public class NewInstanceAndDeployWarResponse extends Response{

    private String instanceId;

    private String instanceIp;

    private String agentIp;

    private int httpPort;

    private String token;

    private String appId;

    private String agentHostname;

    private String instanceHostname;

    private Long instanceIndex;

    private OperationInfo operationInfo;

}
