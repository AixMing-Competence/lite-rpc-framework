package com.aixming.rpc.registry;

import com.aixming.rpc.config.RegistryConfig;
import com.aixming.rpc.model.ServiceMetaInfo;

import java.util.List;

/**
 * 注册中心
 *
 * @author AixMing
 * @since 2024-12-26 20:03:40
 */
public interface Registry {

    /**
     * 初始化注册中心
     *
     * @param registryConfig
     */
    void init(RegistryConfig registryConfig);

    /**
     * 注册服务（服务端）
     *
     * @param serviceMetaInfo
     * @throws Exception
     */
    void register(ServiceMetaInfo serviceMetaInfo) throws Exception;

    /**
     * 注销服务（服务端）
     *
     * @param serviceMetaInfo
     */
    void unRegister(ServiceMetaInfo serviceMetaInfo);

    /**
     * 服务发现（获取某服务的所有节点，消费端）
     *
     * @param serviceKey
     * @return
     */
    List<ServiceMetaInfo> serviceDiscovery(String serviceKey);

    /**
     * 服务销毁
     */
    void destroy();

}
