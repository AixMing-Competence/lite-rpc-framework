package com.aixming.literpcspringbootstarter.annotation;

import com.aixming.literpcspringbootstarter.bootstrap.RpcConsumerBootstrap;
import com.aixming.literpcspringbootstarter.bootstrap.RpcInitBootstrap;
import com.aixming.literpcspringbootstarter.bootstrap.RpcProviderBootstrap;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启动 Rpc 注解
 *
 * @author AixMing
 * @since 2025-01-15 14:15:23
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({RpcInitBootstrap.class, RpcProviderBootstrap.class, RpcConsumerBootstrap.class})
public @interface EnableRpc {

    /**
     * 需要启动 server
     *
     * @return
     */
    boolean needServer() default true;

}
