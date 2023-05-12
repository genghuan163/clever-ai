package com.jjh.cleverai.cache;
import java.util.concurrent.ExecutionException;

import org.apache.http.client.utils.CloneUtils;
import org.springframework.beans.factory.InitializingBean;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
public abstract class AbstractGuavaCache<PK, T> implements InitializingBean {

    private LoadingCache<PK, T> cache;

    protected abstract CacheBuilder<Object, Object> getCacheBuilder(CacheBuilder<Object, Object> cacheBuilder);

    protected abstract CacheLoader<PK, T> getCacheLoader();

    protected LoadingCache<PK, T> getCache() {
        return cache;
    }

    public T getValue(PK pk) throws Exception {
        try {
            return CloneUtils.cloneObject(this.cache.get(pk));
        } catch (CloneNotSupportedException | ExecutionException e) {
            throw new Exception(e);
        }
    }

    public void setValue(PK pk, T t) {
        this.cache.put(pk, t);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        CacheLoader<PK, T> cacheLoader = this.getCacheLoader();
        CacheBuilder<Object, Object> cacheBuilder = this.getCacheBuilder(CacheBuilder.newBuilder());
        this.cache = cacheBuilder.build(cacheLoader);
    }

}

