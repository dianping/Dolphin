package com.dianping.paas.proc.enums;

import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.HashMap;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2016/02/01 15:20.
 */
public enum ProcStatus {


    RUNNING("State:\tR (running)"),
    SLEEPING("State:\tS (sleeping)"),
    ZOMBIE("State:\tZ (zombie)"),
    DEAD("State:\tX (dead)"),
    STOPPED("State:\tT (stopped)"),
    DISK_SLEEP("State:\tD (disk sleep)"),
    TRACING_STOP("State:\tT (tracing stop)");


    @Getter
    private final String statusStr;

    ProcStatus(String statusStr) {
        this.statusStr = statusStr;
    }

    public static final HashMap<String, ProcStatus> STATUS_STR_MAP = Maps.newHashMap();

    public static ProcStatus convertStatus(String statusStr) {
        ProcStatus procStatus = STATUS_STR_MAP.get(statusStr);

        // 默认设置为dead状态
        if (procStatus == null) {
            procStatus = DEAD;
        }

        return procStatus;
    }

    static {
        for (ProcStatus procStatus : ProcStatus.values()) {
            STATUS_STR_MAP.put(procStatus.getStatusStr(), procStatus);
        }
    }


}
