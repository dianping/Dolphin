package com.dianping.paas.core.dal.entity;

import lombok.Data;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 16/2/15.
 */
@Data
public class OperationDetailEntity {

    private long id;

    private long operationId;

    private int totalStep;

    private int doneStep;

    private String rawLog;

    private String msg;

    private long keyId;

}
