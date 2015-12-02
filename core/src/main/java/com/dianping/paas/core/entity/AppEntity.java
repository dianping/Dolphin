package com.dianping.paas.core.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by yuchao on 15/11/2.
 */
@Data
public class AppEntity extends Entity {
    private String app_id;
    private String type;
    private String owner;
    private Long quota_id;
    private Integer level;
    private String app_plan_name;
    private Date creation_date;
    private Date last_modified_date;
    private String image;
    private String machine_label;

    public AppEntity(String app_id) {
        this.app_id = app_id;
    }

    public AppEntity() {
    }
}
