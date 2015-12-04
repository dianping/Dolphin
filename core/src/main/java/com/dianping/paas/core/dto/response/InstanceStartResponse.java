package com.dianping.paas.core.dto.response;

import lombok.Data;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/12/02 14:36.
 */
@Data
public class InstanceStartResponse extends Response {
    private String containerId;

    @Override
    public String toString() {
        return "InstanceStartResponse{" +
                "containerId='" + containerId + '\'' +
                "} " + super.toString();
    }
}
