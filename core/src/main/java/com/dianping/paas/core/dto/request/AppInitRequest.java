package com.dianping.paas.core.dto.request;

import lombok.Data;

import java.util.Date;

/**
 * Created by yuchao on 11/30/15.
 */
@Data
public class AppInitRequest {
    private String appId;

    private String type;

    private int level;

    private String owner;

    private String appPlanName;

    private Date creationDate;

    private Date lastModifiedDate;

    private String image;

    private String warmUpURL;

    private String ldapBase;

    private String phoenixKernelVersion;

    private long appPlanId;

    private String machineLabel;

    private int instanceCount;
}
