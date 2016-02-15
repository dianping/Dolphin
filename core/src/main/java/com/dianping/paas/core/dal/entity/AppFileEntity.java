package com.dianping.paas.core.dal.entity;

import lombok.Data;

import java.util.Date;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 16/2/2.
 */
@Data
public class AppFileEntity {
    private long id;

    private String localPath;

    private String token;

    private String appId;

    private String appVersion;

    private String uploadUrl;

    private String downloadUrl;

    private String md5;

    private Date creationDate;

    private Date uploadDate;

    private String backupStatus;

}
