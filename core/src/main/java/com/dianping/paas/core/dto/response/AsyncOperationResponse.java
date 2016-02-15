package com.dianping.paas.core.dto.response;

import lombok.Data;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 15/12/15.
 */
@Data
public class AsyncOperationResponse extends Response{

    private long operationId;
}
