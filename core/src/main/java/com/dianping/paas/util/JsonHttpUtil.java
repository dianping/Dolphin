package com.dianping.paas.util;

import com.fasterxml.jackson.core.type.TypeReference;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 2015/11/16 16:24.
 */
public class JsonHttpUtil {

    public static <REQUEST_T, RESPONSE_T> RESPONSE_T postOne(String url, REQUEST_T requestBodyObject, Class<RESPONSE_T> responseType) throws Exception {
        String requestBodyStr = JsonUtil.toJson(requestBodyObject);
        String responseStr = HttpUtil.post(url, requestBodyStr);

        return JsonUtil.toBean(responseStr, responseType);
    }


    public static <RESPONSE_T> RESPONSE_T postOne(String url, Class<RESPONSE_T> responseType) throws Exception {
        String responseStr = HttpUtil.post(url);

        return JsonUtil.toBean(responseStr, responseType);
    }

    public static <REQUEST_T, RESPONSE_T> RESPONSE_T postCollection(String url, REQUEST_T requestBodyObject, TypeReference<RESPONSE_T> typeReference) throws Exception {
        String requestBodyStr = JsonUtil.toJson(requestBodyObject);

        String responseStr = HttpUtil.post(url, requestBodyStr);

        return JsonUtil.toCollectionBean(responseStr, typeReference);
    }

    public static <RESPONSE_T> RESPONSE_T postCollection(String url, TypeReference<RESPONSE_T> typeReference) throws Exception {
        String responseStr = HttpUtil.post(url);

        return JsonUtil.toCollectionBean(responseStr, typeReference);
    }


    public static <RESPONSE_T> RESPONSE_T getOne(String url, Class<RESPONSE_T> responseType) throws Exception {
        String responseStr = HttpUtil.get(url);

        return JsonUtil.toBean(responseStr, responseType);
    }

    public static <RESPONSE_T> RESPONSE_T getCollection(String url, TypeReference<RESPONSE_T> typeReference) throws Exception {
        String responseStr = HttpUtil.get(url);
        return JsonUtil.toCollectionBean(responseStr, typeReference);
    }
}

