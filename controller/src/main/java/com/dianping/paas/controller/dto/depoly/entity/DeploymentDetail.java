package com.dianping.paas.controller.dto.depoly.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 16/2/15.
 */
@Data
public class DeploymentDetail {
    private String appId;

    private int totalHosts;

    private int status;

    private List<HostOperation> m_hostOperations = new ArrayList<HostOperation>();
}
