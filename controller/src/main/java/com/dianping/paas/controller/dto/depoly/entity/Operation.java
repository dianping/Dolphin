package com.dianping.paas.controller.dto.depoly.entity;


import lombok.Data;

import java.util.Date;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 15/12/15.
 */
@Data
public class Operation {
    private long id;

    private String appId;

    private int type;

    private int state;

    private Date creationDate;

    private Date lastModifiedDate;

    public static final int STATUS_NOT_STARTED = 0;
    public static final int STATUS_IN_PROGRESS = 100;
    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_FAIL = 500;
    public static final int STATUS_UNKNOWN = 400;
    public static final int TYPE_CREATE = 1;
    public static final int TYPE_UPGRADE = 2;
    public static final int TYPE_SHUTDOWN = 3;
    public static final int TYPE_START = 4;
    public static final int TYPE_REMOVE = 5;
    public static final int TYPE_UPGRADE_KERNEL = 6;
    public static final int TYPE_RESTART = 7;
    public static final int TYPE_SHUTDOWN_AGENT = 8;
    public static final int TYPE_STARTUP_AGENT = 9;
    public static final int TYPE_BLACKHOLE_LOGOUT = 10;
}
