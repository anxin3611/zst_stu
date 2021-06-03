package com.zst.spring.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
    public static final String DATE_TIME_SECOND_STRING = "yyyy-MM-dd HH:mm:ss";

    /**
     * {@link OffsetDateTime} 序列化器
     *
     * @return {@link JsonSerializer<OffsetDateTime>} 序列化器
     */
    @Bean
    public JsonSerializer<OffsetDateTime> localDateTimeSerializer() {
        return new JsonSerializer<OffsetDateTime>() {
            @Override
            public void serialize(OffsetDateTime offsetDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeString(offsetDateTime.format(getDateTimeFormatter()));
            }
        };
    }

    /**
     * {@link OffsetDateTime} 反序列化器
     *
     * @return {@link JsonDeserializer<OffsetDateTime>} 序列化器
     */
    @Bean
    public JsonDeserializer<OffsetDateTime> localDateTimeDeserializer() {
        return new JsonDeserializer<OffsetDateTime>() {
            @Override
            public OffsetDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                final LocalDateTime parse = LocalDateTime.parse(jsonParser.getText(), getDateTimeFormatter());
                return ZonedDateTime.of(parse, ZoneId.systemDefault()).toOffsetDateTime();
            }
        };
    }

    /**
     * 设置Jackson2序列化方式
     * @return {@link Jackson2ObjectMapperBuilderCustomizer}
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            builder.serializerByType(OffsetDateTime.class, localDateTimeSerializer());
            builder.deserializerByType(OffsetDateTime.class, localDateTimeDeserializer());
            builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        };
    }

    /**
     * 消息转换器配置
     *
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
        /*
        WriteNullListAsEmpty ：List字段如果为null,输出为[],而非null
        WriteNullStringAsEmpty ： 字符类型字段如果为null,输出为"",而非null
        DisableCircularReferenceDetect ：消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
        WriteNullBooleanAsFalse：Boolean字段如果为null,输出为false,而非null
        WriteMapNullValue：是否输出值为null的字段,默认为false。*/
        fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
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
        // 添加消息转换
        converters.add(fastJsonHttpMessageConverter);
    }

    /**
     * 时间格式化
     *
     * @return 时间格式化
     */
    private DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern(DATE_TIME_SECOND_STRING).withZone(ZoneId.systemDefault());
    }
}
