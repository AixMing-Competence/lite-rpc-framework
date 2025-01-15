package com.aixming.literpcspringbootstarter.bootstrap;

import com.aixming.literpcspringbootstarter.annotation.RpcService;
import com.aixming.rpc.RpcApplication;
import com.aixming.rpc.config.RpcConfig;
import com.aixming.rpc.model.ServiceMetaInfo;
import com.aixming.rpc.registry.LocalRegistry;
import com.aixming.rpc.registry.Registry;
import com.aixming.rpc.registry.RegistryFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Rpc 服务提供者启动
 *
 * @author AixMing
 * @since 2025-01-15 15:02:18
 */
public class RpcProviderBootstrap implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        RpcService rpcService = beanClass.getAnnotation(RpcService.class);
        if (rpcService != null) {
            // 获取服务基本信息
            Class<?> interfaceClass = rpcService.interfaceClass();
            // 默认值处理
            if (interfaceClass == void.class) {
                interfaceClass = beanClass.getInterfaces()[0];
            }
            String serviceName = interfaceClass.getName();

            // 本地注册
            LocalRegistry.register(serviceName, beanClass);

            // 注册到注册中心
            RpcConfig rpcConfig = RpcApplication.getRpcConfig();
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceVersion(rpcService.serviceVersion());
            serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
            serviceMetaInfo.setServicePort(rpcConfig.getServerPort());

            Registry registry = RegistryFactory.getInstance(rpcConfig.getRegistryConfig().getRegistry());
            try {
                registry.register(serviceMetaInfo);
            } catch (Exception e) {
                throw new RuntimeException(serviceName + " 服务注册失败", e);
            }
        }
        
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
