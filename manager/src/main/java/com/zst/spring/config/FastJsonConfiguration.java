package com.zst.spring.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @author ZST
 * @version 1.0
 * @date 2020/1/15 10:34
 * @description fastJson 配置
 * <p>
 * WevMvcConfigurer 是Spring内部的一种配置方式
 * 有两种方式实现，
 * implements WevMvcConfigurer
 * extends WebMvcConfigurationSupport
 */
@Configuration
public class FastJsonConfiguration extends WebMvcConfigurationSupport {

    /**
     * 消息转换器配置
     * @param converters 转换器集合
     */
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 父类的消息转换
        super.configureMessageConverters(converters);
        // fastJson的消息转换
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        // fasttJson消息转换配置
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        // 设置转换的格式
        /*WriteNullListAsEmpty  ：List字段如果为null,输出为[],而非null
        WriteNullStringAsEmpty ： 字符类型字段如果为null,输出为"",而非null
        DisableCircularReferenceDetect ：消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
        WriteNullBooleanAsFalse：Boolean字段如果为null,输出为false,而非null
        WriteMapNullValue：是否输出值为null的字段,默认为false。*/
        fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        // 添加消息转换
        converters.add(fastJsonHttpMessageConverter);
    }
}
