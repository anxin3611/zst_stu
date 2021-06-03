/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */

package com.zst.spring.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import static com.zst.spring.util.constant.Constants.REDIS_DB_ONE;
import static com.zst.spring.util.constant.Constants.REDIS_DB_ZERO;

/**
 * @author ：weiwei
 * @version ：V1.0
 * @program ：vevor-scp
 * @date ：Created in 2020/5/30 1:42 下午
 * @description ：inventory 统一的redis配置
 */
@Slf4j
@Configuration
public class RedisConfig {

    @Value("${spring.redis.lettuce.pool.max-active}")
    private int maxActive;
    @Value("${spring.redis.lettuce.pool.max-wait}")
    private int maxWait;
    @Value("${spring.redis.lettuce.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.lettuce.pool.min-idle}")
    private int minIdle;

    /**
     * 生成库存模块所需用到的redis template对象
     *
     * @param connectionFactory Lettuce连接工厂
     * @return inventoryRedisTemplate
     */
    @Bean(name = "ZeroRedisTemplate")
    public RedisTemplate<String, Object> inventoryRedisTemplate(LettuceConnectionFactory connectionFactory) {
        LettuceConnectionFactory lettuceConnectionFactory = createConnectionFactory(connectionFactory.getStandaloneConfiguration(), REDIS_DB_ZERO);
        RedisTemplate<String, Object> redisTemplate = createRedisTemplate(lettuceConnectionFactory);

        log.info("生成<String, Object>inventoryRedisTemplate, with:host={}, port={}, password={}, database={},",
                connectionFactory.getHostName(), connectionFactory.getPort(),
                connectionFactory.getPassword(), connectionFactory.getDatabase());
        return redisTemplate;
    }

    /**
     * 生成订单模块所需用到的redis template对象
     *
     * @param connectionFactory Lettuce连接工厂
     * @return inventoryRedisTemplate
     */
    @Bean(name = "OrderRedisTemplate")
    public RedisTemplate<String, Object> orderRedisTemplate(LettuceConnectionFactory connectionFactory) {
        LettuceConnectionFactory lettuceConnectionFactory = createConnectionFactory(connectionFactory.getStandaloneConfiguration(), REDIS_DB_ONE);
        RedisTemplate<String, Object> redisTemplate = createRedisTemplate(lettuceConnectionFactory);

        log.info("生成<String, Object>orderRedisTemplate, with:host={}, port={}, password={}, database={},",
                connectionFactory.getHostName(), connectionFactory.getPort(),
                connectionFactory.getPassword(), connectionFactory.getDatabase());
        return redisTemplate;
    }

    /**
     * 创建redisTemplate <p>
     * 该方法不能加 @Bean 否则不管如何调用，connectionFactory都会是默认配置
     *
     * @param redisConnectionFactory 标准的redis连接工厂
     * @return RedisTemplate
     */
    private RedisTemplate<String, Object> createRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.WRAPPER_ARRAY);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    /**
     * 根据标准连接配置+数据库编号，和配置文件中的连接池配置，生成Lettuce连接工厂
     *
     * @param redisStandaloneConfiguration 标准连接配置
     * @param database                     数据库编号，取自RedisConstant中的配置项
     * @return LettuceConnectionFactory：Lettuce连接工厂
     */
    private LettuceConnectionFactory createConnectionFactory(RedisStandaloneConfiguration redisStandaloneConfiguration, int database) {
        /* ========= 连接池通用配置 ========= */
        GenericObjectPoolConfig<Object> genericObjectPoolConfig = new GenericObjectPoolConfig<>();
        genericObjectPoolConfig.setMaxTotal(maxActive);
        genericObjectPoolConfig.setMinIdle(minIdle);
        genericObjectPoolConfig.setMaxIdle(maxIdle);
        genericObjectPoolConfig.setMaxWaitMillis(maxWait);

        /* ========= lettuce pool ========= */
        LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder builder = LettucePoolingClientConfiguration.builder();
        builder.poolConfig(genericObjectPoolConfig);
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration, builder.build());
        connectionFactory.setDatabase(database);
        connectionFactory.afterPropertiesSet();

        return connectionFactory;
    }
}
