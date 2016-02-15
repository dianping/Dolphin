package com.dianping.paas.controller.exception;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 15/12/15.
 */
public class AppExistException extends Exception {
    public AppExistException(String msg) {
        super(msg);
    }
}
