package com.aixming.rpc.registry;

import com.aixming.rpc.model.ServiceMetaInfo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 注册服务本地缓存（单个服务）
 *
 * @author AixMing
 * @since 2025-01-02 21:28:54
 */
public class RegistryServiceCache {

    /**
     * 服务缓存
     */
    private List<ServiceMetaInfo> serviceCache = new ArrayList<>();

    /**
     * 写缓存
     *
     * @param serviceMetaInfoList
     */
    public void writeCache(List<ServiceMetaInfo> serviceMetaInfoList) {
        serviceCache = serviceMetaInfoList;
    }

    /**
     * 读缓存
     */
    public List<ServiceMetaInfo> readCache() {
        return serviceCache;
    }

    /**
     * 清空缓存
     */
    public void clearCache() {
        serviceCache = null;
    }

}
