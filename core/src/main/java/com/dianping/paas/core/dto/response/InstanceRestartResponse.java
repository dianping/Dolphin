package com.dianping.paas.core.dto.response;

import lombok.Data;

/**
 * Created by yuchao on 12/4/15.
 */
@Data
public class InstanceRestartResponse extends Response {
    @Override
    public String toString() {
        return "InstanceRestartResponse{} " + super.toString();
    }
}
