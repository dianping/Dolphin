package com.dianping.paas.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * yapu.wang@dianping.com
 * Created by wangyapu on 16/2/15.
 */
public class DateHelper {

    private static final BlockingQueue<SimpleDateFormat> formats = new ArrayBlockingQueue<SimpleDateFormat>(20);

    private static final Map<String, Long> map = new ConcurrentHashMap<String, Long>();

    public static String format(long timestamp) {
        SimpleDateFormat format = formats.poll();

        if (format == null) {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        }

        try {
            return format.format(new Date(timestamp));
        } finally {
            if (formats.remainingCapacity() > 0) {
                formats.offer(format);
            }
        }
    }

    public static long parse(String str) {
        int len = str.length();
        String date = str.substring(0, 10);
        Long baseline = map.get(date);

        if (baseline == null) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                baseline = format.parse(date).getTime();
                map.put(date, baseline);
            } catch (ParseException e) {
                return -1;
            }
        }

        long time = baseline.longValue();
        long metric = 1;
        boolean millisecond = true;

        for (int i = len - 1; i > 10; i--) {
            char ch = str.charAt(i);

            if (ch >= '0' && ch <= '9') {
                time += (ch - '0') * metric;
                metric *= 10;
            } else if (millisecond) {
                millisecond = false;
            } else {
                metric = metric / 100 * 60;
            }
        }
        return time;
    }

}
