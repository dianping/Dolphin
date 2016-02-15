package com.dianping.paas.core.dal.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by yuchao on 12/1/15.
 */
@Data
public class ImageEntity {
    private long id;
    private String imageType;
    private String dockerfileTemplate;
    private Date creationDate;
    private Date lastModifiedDate;
}
