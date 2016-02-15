package com.dianping.paas.controller.dto.depoly.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 16/2/15.
 */
@Data
public class HostOperation {
    private String type;

    private String agentIp;

    private String instanceIp;

    private int port;

    private int status;

    private long dbInstanceId;

    private String reason;

    private String appVersion;

    private String instanceId;

    private long instanceGroupId;

    private String image;

    private long instanceIndex;

    private List<Segment> segments = new ArrayList<Segment>();

    private String appId;

    private String appKernelVersion;

    public static final int STATUS_NOT_STARTED = 0;

    public static final int STATUS_IN_PROGRESS = 100;

    public static final int STATUS_SUCCESS = 200;

    public static final int STATUS_FAIL = 500;

    public static final int STATUS_UNKNOWN = 400;

    public static final String TYPE_CREATE = "create";

    public static final String TYPE_SHUTDOWN = "shutdown";

    public static final String TYPE_UPGRADE = "upgrade";

    public static final String TYPE_START = "start";

    public static final String TYPE_RESTART = "restart";

    public static final String TYPE_REMOVE = "remove";

    public static final String TYPE_UPGRADE_KERNEL = "upgrade_kernel";

    public boolean isSuccess() {
        return status == STATUS_SUCCESS;
    }
}
