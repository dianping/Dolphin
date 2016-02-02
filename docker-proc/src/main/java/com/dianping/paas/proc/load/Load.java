package com.dianping.paas.proc.load;

import lombok.Data;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2016/01/29 10:27.
 */
@Data
public class Load {
    private long load1;

    private long load5;

    private long load15;

    public Load(long load1, long load5, long load15) {
        this.load1 = load1;
        this.load5 = load5;
        this.load15 = load15;
    }

    public Load() {
        this(0, 0, 0);
    }

}
