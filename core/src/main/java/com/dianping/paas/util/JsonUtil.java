package com.dianping.paas.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 15/11/2.
 */
public class JsonUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> T toCollectionBean(String json) throws IOException {

        return toCollectionBean(json, new TypeReference<T>() {
        });
    }

    public static <T> T toCollectionBean(String json, TypeReference<T> typeReference) throws IOException {

        return MAPPER.readValue(json, typeReference);
    }

    public static <T> T toBean(String json, Class<T> requireType) throws IOException {

        return MAPPER.readValue(json, requireType);
    }

    public static <T> String toJson(T bean) throws IOException {
        StringWriter stringWriter = new StringWriter();
        MAPPER.writeValue(stringWriter, bean);

        return stringWriter.toString();
    }

}