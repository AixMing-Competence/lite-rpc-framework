package com.aixming.rpc.loadbalancer;

import com.aixming.rpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 一致性哈希负载均衡器
 *
 * @author AixMing
 * @since 2025-01-10 15:57:37
 */
public class ConsistentHashLoadBalancer implements LoadBalancer {

    /**
     * 虚拟节点
     */
    private final TreeMap<Integer, ServiceMetaInfo> virtualNodes = new TreeMap<>();

    /**
     * 虚拟节点数
     */
    private static final int VIRTUAL_NODE_NUM = 100;

    @Override
    public ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList) {
        if (serviceMetaInfoList.isEmpty()) {
            return null;
        }

        // 初始化虚拟节点环
        // 每次调用负载均衡器时，都会重新构造 Hash 环，能够及时处理节点的变化
        for (ServiceMetaInfo serviceMetaInfo : serviceMetaInfoList) {
            for (int i = 0; i < VIRTUAL_NODE_NUM; i++) {
                int hashCode = getHash(serviceMetaInfo.getServiceAddress() + "#" + i);
                virtualNodes.put(hashCode, serviceMetaInfo);
            }
        }

        // 根据该请求的 hash 值
        int hashCode = getHash(requestParams);

        // 从虚拟节点环中获取第一个大于等于该请求 hashCode 的服务
        Map.Entry<Integer, ServiceMetaInfo> entry = virtualNodes.ceilingEntry(hashCode);
        if (entry == null) {
            entry = virtualNodes.firstEntry();
        }
        return entry.getValue();
    }

    /**
     * 计算 hash 值
     *
     * @param key
     * @return
     */
    private int getHash(Object key) {
        return key.hashCode();
    }

}
