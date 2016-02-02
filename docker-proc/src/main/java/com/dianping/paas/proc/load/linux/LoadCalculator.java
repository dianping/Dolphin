package com.dianping.paas.proc.load.linux;

import com.dianping.paas.proc.load.Load;
import com.dianping.paas.proc.load.TaskInfo;

/**
 * linux 内核计算load的算法
 * chao.yu@dianping.com
 * Created by yuchao on 2016/01/28 20:16.
 */
public class LoadCalculator {
    public static final int FSHIFT = 11;
    public static final int FIXED_1 = (1 << FSHIFT);
    public static final int EXP_1 = 1884;
    public static final int EXP_5 = 2014;
    public static final int EXP_15 = 2037;

    private static long calcLoad(long load, long exp, long active) {
        load *= exp;
        load += active * (FIXED_1 - exp);
        load += 1 << (FSHIFT - 1);
        return load >> FSHIFT;
    }


    public static String calcLoad(Load load, TaskInfo taskInfo) {
        long active_tasks = FIXED_1 * taskInfo.getRunningTaskCount();

        load.setLoad1(calcLoad(load.getLoad1(), EXP_1, active_tasks));
        load.setLoad5(calcLoad(load.getLoad5(), EXP_5, active_tasks));
        load.setLoad15(calcLoad(load.getLoad15(), EXP_15, active_tasks));

        return getLoadStr(load, taskInfo);
    }


    private static Load getLoad(Load pLoad, long offset, int shift) {
        Load load = new Load();

        load.setLoad1((pLoad.getLoad1() + offset) << shift);
        load.setLoad5((pLoad.getLoad5() + offset) << shift);
        load.setLoad15((pLoad.getLoad15() + offset) << shift);

        return load;
    }


    private static long getLoadInt(long x) {
        return x >> FSHIFT;
    }


    private static long getLoadFrac(long x) {
        return getLoadInt((x & (FIXED_1 - 1)) * 100);
    }

    private static String getLoadStr(Load pLoad, TaskInfo taskInfo) {
        Load load = getLoad(pLoad, FIXED_1 / 200, 0);

        return String.format("%d.%02d %d.%02d %d.%02d %d/%d 99999\n",
                getLoadInt(load.getLoad1()), getLoadFrac(load.getLoad1()),
                getLoadInt(load.getLoad5()), getLoadFrac(load.getLoad5()),
                getLoadInt(load.getLoad15()), getLoadFrac(load.getLoad15()),
                taskInfo.getRunningTaskCount(), taskInfo.getTotalTaskCount());
    }
}
