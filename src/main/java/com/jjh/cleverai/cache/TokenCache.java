package com.jjh.cleverai.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;


import com.jjh.cleverai.model.TUsers;
import org.springframework.stereotype.Component;


@Component
public class TokenCache extends AbstractGuavaCache<String, Map<String, TUsers>> {

    // 过期时间: 1小时
    private static final int EXPIRE_SEC_TIME = 30;

    // 最多保存的key的数量
    private static final int MAX_KEY_SIZE = 500;
    @Override
    protected CacheBuilder<Object, Object> getCacheBuilder(CacheBuilder<Object, Object> cacheBuilder) {
        return cacheBuilder.maximumSize(MAX_KEY_SIZE).expireAfterWrite(EXPIRE_SEC_TIME, TimeUnit.MINUTES);
    }

    @Override
    protected CacheLoader<String, Map<String, TUsers>> getCacheLoader() {
        return new CacheLoader<String, Map<String, TUsers>>() {

            @Override
            public Map<String, TUsers> load(String key) {
                return new HashMap<>();
            }
        };
    }
    public TUsers genToken(String key) throws Exception {

        return super.getValue(key).get(key);
    }
    public void setCache(String key,TUsers users) {
        Map<String, TUsers> tokenMap = new HashMap<>();
        tokenMap.put(key, users);
        super.setValue(key, tokenMap);
    }
}