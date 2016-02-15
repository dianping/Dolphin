package com.dianping.paas.core.dal.entity;

import lombok.Data;

import java.util.Date;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 15/12/15.
 */
@Data
public class AppConfigEntity {

    private long id;

    private String appId;

    private String warmUpUrl;

    private String ldapBase;

    private String phoenixKernelVersion;

    private Date creationDate;

    private Date lastModifiedDate;
}
