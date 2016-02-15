package com.dianping.paas.core.dal.entity;

import lombok.Data;

import java.util.Date;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/15 14:05.
 */
@Data
public class InstanceEntity {
    private int id;
    private String instanceId;
    private long instanceGroupId;
    private String appId;
    private String appVersion;
    private String instanceIp;
    private String agentIp;
    private int instancePort;
    private int type;
    private int status;
    private Date creationDate;
    private Date lastModifiedDate;
    private String image;
    private long instanceIndex;

    public static final int STATUS_NEW = 0;
    public static final int STATUS_CREATING = 100;
    public static final int STATUS_SHUTTINGDOWN = 101;
    public static final int STATUS_STARTING = 102;
    public static final int STATUS_REMOVING = 103;
    public static final int STATUS_UPGRADING = 104;
    public static final int STATUS_UPGRADING_KERNEL = 105;
    public static final int STATUS_RESTARTING = 106;
    public static final int STATUS_RUNNING = 200;
    public static final int STATUS_REMOVED = 400;
    public static final int STATUS_FAULT = 404;
    public static final int STATUS_SHUTDOWN = 500;
    public static final int STATUS_CREATE_FAIL = 501;
    public static final int STATUS_ONLINE_FAIL = 502;
    public static final int STATUS_UPGRADE_FAIL = 503;
    public static final int STATUS_SHUTDOWN_FAIL = 504;
    public static final int STATUS_START_FAIL = 505;
    public static final int STATUS_REMOVE_FAIL = 506;
    public static final int STATUS_UPGRADE_KERNEL_FAIL = 507;
    public static final int STATUS_UPGRADE_KERNEL_ROLLBACK_FAIL = 508;
    public static final int STATUS_RESTART_FAIL = 509;

    public static final int INSTANCE_TYPE_NORMAL = 1;
}

