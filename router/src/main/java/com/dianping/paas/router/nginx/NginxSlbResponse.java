package com.dianping.paas.router.nginx;

import lombok.Data;

@Data
public class NginxSlbResponse {
    public static final int SUCCESS = 0;

    private static final int MEMBER_NOT_FOUND = -1;
    private static final int POOL_NOT_FOUND = -2;
    private static final int NOT_SUCCESS_ALL = -4;


    private int errorCode;

    private String message;

    private int taskId;

    public boolean success() {
        return errorCode == SUCCESS;
    }

    public boolean memberNotFound() {
        return errorCode == MEMBER_NOT_FOUND;
    }

    public boolean poolNotFound() {
        return errorCode == POOL_NOT_FOUND;
    }

    public boolean notSuccessAll() {
        return errorCode == NOT_SUCCESS_ALL;
    }

    public boolean unknownError() {
        return errorCode > 0;
    }
}