package com.aixming.rpc.registry;

import com.aixming.rpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册服务本地缓存（多个服务）
 *
 * @author AixMing
 * @since 2025-01-02 21:28:54
 */
public class RegistryServiceMultiCache {

    /**
     * 服务缓存
     */
    private Map<String, List<ServiceMetaInfo>> serviceCache = new ConcurrentHashMap<>();

    /**
     * 写缓存
     *
     * @param serviceKey
     * @param newServiceCache
     */
    public void writeCache(String serviceKey, List<ServiceMetaInfo> newServiceCache) {
        serviceCache.put(serviceKey, newServiceCache);
    }

    /**
     * 读缓存
     *
     * @param serviceKey
     * @return
     */
    public List<ServiceMetaInfo> readCache(String serviceKey) {
        return serviceCache.get(serviceKey);
    }

    /**
     * 清空缓存
     *
     * @param serviceKey
     */
    public void clearCache(String serviceKey) {
        serviceCache.remove(serviceKey);
    }

}
