package com.anhvv.base.security.session;

import java.util.HashMap;
import java.util.Map;

public class SessionCache {
    private static Map<String,Object> localCache = new HashMap<>();

    public static void addAttribute(String key, Object value) {
        synchronized (SessionCache.class) {
            localCache.put(key, value);
        }
    }

    public static Object getAttribute(String key) {
        synchronized (SessionCache.class) {
           return localCache.get(key);
        }
    }
}
