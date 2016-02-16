package com.dianping.paas.repository.enums;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 16/2/16.
 */
public enum PkgBackUpStatus {

    NOT_BACKUPED("NOT_BACKUPED", "未备份"),
    BACKUPED("BACKUPED", "已备份"),
    BACKUPED_AND_DELETED("BACKUPED_AND_DELETED", "已备份且已在本地删除"),
    FILE_NOT_EXIST("FILE_NOT_EXIST", "备份文件的时候发现文件不存在");


    private String code;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private String desc;

    PkgBackUpStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
