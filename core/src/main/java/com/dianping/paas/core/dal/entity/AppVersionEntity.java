package com.dianping.paas.core.dal.entity;

import lombok.Data;

import java.util.Date;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 16/2/2.
 */
@Data
public class AppVersionEntity {

    private long id;

    private String appId;

    private String version;

    private long appFileId;

    private Date creationDate;

    private Date lastModifiedDate;

    private AppFileEntity appFile;

}
