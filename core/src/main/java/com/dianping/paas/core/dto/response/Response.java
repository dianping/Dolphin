package com.dianping.paas.core.dto.response;

import lombok.Data;

/**
 * Created by yuchao on 12/4/15.
 */
@Data
public abstract class Response {
    /**
     * 本地请求是否成功
     */
    private boolean success;

    /**
     * 返回码
     */
    private int status;

    /**
     * fail附加信息
     */
    private String reason = "";


    public void success() {
        success = true;
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
        this.status = status;
        this.reason = reason;
    }
}
