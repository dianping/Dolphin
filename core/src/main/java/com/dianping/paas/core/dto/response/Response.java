package com.dianping.paas.core.dto.response;

import lombok.Data;

/**
 * Created by yuchao on 12/4/15.
 */
@Data
public abstract class Response {

    private boolean success;

    private ResultCode returnCode = ResultCode.INITIAL;

    public void success() {
        success = true;
        returnCode = ResultCode.SUCCESS;
    }

    public void fail(ResultCode returnCode) {
        fail(returnCode.getStatus(), returnCode.getReason());
    }

    public void fail() {
        fail(-1, "");
    }

    public void fail(int status) {
        fail(status, "");
    }

    public void fail(String reason) {
        fail(-1, reason);
    }

    public void fail(int status, String reason) {
        this.success = false;
        this.returnCode.setStatus(status);
        this.returnCode.setReason(reason);
    }
}
