package com.dianping.paas.message.codec;

import java.io.IOException;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/11/13 16:55.
 */
public interface Codec {
    <T> String decode(T payload) throws IOException;

    <T> T encode(String body, Class<T> clazz) throws IOException;
}
