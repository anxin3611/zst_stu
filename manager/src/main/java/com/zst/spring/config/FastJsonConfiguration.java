package com.zst.spring.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Item233
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

        //升级最新版本需加，解决 FastJson报错'Content-Type' cannot contain wildcard type '*'
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        supportedMediaTypes.add(MediaType.APPLICATION_PDF);
        supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XML);
        supportedMediaTypes.add(MediaType.IMAGE_GIF);
        supportedMediaTypes.add(MediaType.IMAGE_JPEG);
        supportedMediaTypes.add(MediaType.IMAGE_PNG);
        supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
        supportedMediaTypes.add(MediaType.TEXT_XML);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);

        // fasttJson消息转换配置
        FastJsonConfig fastJsonConfig = new FastJsonConfig();


        // 设置转换的格式
        /*
        WriteNullListAsEmpty  ：List字段如果为null,输出为[],而非null
        WriteNullStringAsEmpty ： 字符类型字段如果为null,输出为"",而非null
        DisableCircularReferenceDetect ：消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
        WriteNullBooleanAsFalse：Boolean字段如果为null,输出为false,而非null
        WriteMapNullValue：是否输出值为null的字段,默认为false。*/
        fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        // 添加消息转换
        converters.add(fastJsonHttpMessageConverter);
    }

    /**
     * 发现如果继承了WebMvcConfigurationSupport，则在yml中配置的相关内容会失效。 需要重新指定静态资源
     *
     * No mapping for GET /swagger-ui.html，这个异常的出现其实就是因为他按照原来的路径已经找不到了
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
}
