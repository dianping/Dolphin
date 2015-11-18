package com.dianping.paas.util;

/**
 * Created by yuchao on 11/18/15.
 */
public class ArrayUtil {
    public static boolean contains(int[] ignoreErrorCodeArray, int errorCode) {
        if (ignoreErrorCodeArray != null) {
            for (int ignoreErrorCode : ignoreErrorCodeArray) {
                if (ignoreErrorCode == errorCode) {
                    return true;
                }
            }
        }
        return false;
    }
}
