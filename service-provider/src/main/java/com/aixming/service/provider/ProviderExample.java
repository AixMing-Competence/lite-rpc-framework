package com.aixming.service.provider;

import com.aixming.common.service.UserService;
import com.aixming.rpc.bootstrap.ProviderBootstrap;
import com.aixming.rpc.bootstrap.ServiceRegisterInfo;

import java.util.ArrayList;

/**
 * 服务提供者示例
 *
 * @author AixMing
 * @since 2024-12-27 18:43:54
 */
public class ProviderExample {

    public static void main(String[] args) {
        ArrayList<ServiceRegisterInfo<?>> serviceRegisterInfoList = new ArrayList<>();
        ServiceRegisterInfo<UserService> serviceRegisterInfo = new ServiceRegisterInfo(UserService.class.getName(), UserServiceImpl.class);
        serviceRegisterInfoList.add(serviceRegisterInfo);
        
        // 服务提供者初始化
        ProviderBootstrap.init(serviceRegisterInfoList);
    }

}
