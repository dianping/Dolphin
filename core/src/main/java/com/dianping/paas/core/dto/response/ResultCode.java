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
    ERROR(1, "default or unknown error");

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
