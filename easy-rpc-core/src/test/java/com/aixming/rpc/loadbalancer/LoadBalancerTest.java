package com.aixming.rpc.loadbalancer;

import com.aixming.rpc.model.ServiceMetaInfo;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author AixMing
 * @since 2025-01-10 17:34:23
 */
public class LoadBalancerTest {

    private final LoadBalancer loadBalancer = new RandomLoadBalancer();

    @Test
    public void select() {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("methodName", "apple");
        // 服务列表
        ServiceMetaInfo serviceMetaInfo1 = new ServiceMetaInfo();
        serviceMetaInfo1.setServiceName("myService");
        serviceMetaInfo1.setServiceVersion("1.0");
        serviceMetaInfo1.setServiceHost("localhost");
        serviceMetaInfo1.setServicePort(1234);
        ServiceMetaInfo serviceMetaInfo2 = new ServiceMetaInfo();
        serviceMetaInfo2.setServiceName("myService");
        serviceMetaInfo2.setServiceVersion("1.0");
        serviceMetaInfo2.setServiceHost("douyu.com");
        serviceMetaInfo2.setServicePort(80);
        List<ServiceMetaInfo> serviceMetaInfoList = Arrays.asList(serviceMetaInfo1, serviceMetaInfo2);
        ServiceMetaInfo selectedServiceMetaInfo = loadBalancer.select(requestParams, serviceMetaInfoList);
        Assert.assertNotNull(selectedServiceMetaInfo);
        System.out.println(selectedServiceMetaInfo);
        selectedServiceMetaInfo = loadBalancer.select(requestParams,serviceMetaInfoList);
        Assert.assertNotNull(selectedServiceMetaInfo);
        System.out.println(selectedServiceMetaInfo);
        selectedServiceMetaInfo = loadBalancer.select(requestParams,serviceMetaInfoList);
        Assert.assertNotNull(selectedServiceMetaInfo);
        System.out.println(selectedServiceMetaInfo);
    }

}
