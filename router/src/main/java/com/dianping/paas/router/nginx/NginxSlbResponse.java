package com.dianping.paas.router.nginx;

import lombok.Data;

@Data
public class NginxSlbResponse {
    public static final int SUCCESS = 0;
    public static final int FAIL = -8;

    public static final int MEMBER_NOT_FOUND = -1;
    public static final int POOL_NOT_FOUND = -2;
    public static final int NOT_SUCCESS_ALL = -4;


    private int errorCode = FAIL;

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

    public String getErrorDesc() {
        String desc;
        switch (errorCode) {
            case MEMBER_NOT_FOUND:
                desc = "MEMBER_NOT_FOUND";
                break;
            case POOL_NOT_FOUND:
                desc = "POOL_NOT_FOUND";
                break;
            case NOT_SUCCESS_ALL:
                desc = "NOT_SUCCESS_ALL";
                break;
            default:
                desc = "";
        }
        return desc;
    }
}