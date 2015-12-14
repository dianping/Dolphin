package com.dianping.paas.core.extension;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * chao.yu@dianping.com
 * Created by yuchao on 15/11/2.
 */
public final class ExtensionLoader {
    private static final Logger logger = LogManager.getLogger(ExtensionLoader.class);

    private static final Map<Class<?>, Object> extensionMap = new ConcurrentHashMap<Class<?>, Object>();

    public static <T> T getExtension(Class<T> clazz) {
        T extension = (T) extensionMap.get(clazz);

        if (extension == null) {
            extension = newExtension(clazz);
            if (extension != null) {
                extensionMap.put(clazz, extension);
            }
        }

        return extension;
    }


    public static <T> T newExtension(Class<T> clazz) {
        ServiceLoader<T> serviceLoader = ServiceLoader.load(clazz);

        for (T service : serviceLoader) {
            return service;
        }

        logger.error("no extension found for class:" + clazz.getName());

        return null;
    }
}
