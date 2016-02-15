package com.dianping.paas.core.dal.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by yuchao on 15/11/2.
 */
@Data
public class AppEntity {
    private long id;
    private String appId;
    private String type;
    private String owner;
    private Long quotaId;
    private int level;
    private String appPlanName;
    private Date creationDate;
    private Date lastModifiedDate;
    private String image;
    private String machineLabel;
    private QuotaEntity quota;

    public AppEntity(String appId) {
        this.appId = appId;
    }

    public AppEntity() {
    }
}
