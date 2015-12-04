package com.dianping.paas.core.dto.response;

import lombok.Data;

/**
 * Created by yuchao on 12/3/15.
 */
@Data
public class AllocateWebPackageResponse extends Response {

    private String uploadUrl;

    @Override
    public String toString() {
        return "AllocateWebPackageResponse{" +
                "uploadUrl='" + uploadUrl + '\'' +
                "} " + super.toString();
    }
}
