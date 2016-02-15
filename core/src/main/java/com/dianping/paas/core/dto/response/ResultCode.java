package com.dianping.paas.core.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 15/12/15.
 */
public enum ResultCode {

    INITIAL(0, "INITIAL"),

    /**
     * success
     */
    SUCCESS(200, "success"),

    /**
     * 1~99: general error
     */
    ERROR(1, "default or unknown error"),

    /**
     * 1001~9999: database error
     */
    DB_APP_PLAN_NOT_EXIST(1000, "the appPlanId is not valid, can not find it database"),

    DB_APP_EXIST(1001,"the app already exists");


    @Getter
    @Setter
    private int status;

    @Getter
    @Setter
    private String reason;

    ResultCode(int status, String reason) {
        this.status = status;
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "ResultCode{" +
                "status=" + status +
                ", reason='" + reason + '\'' +
                '}';
    }
}
