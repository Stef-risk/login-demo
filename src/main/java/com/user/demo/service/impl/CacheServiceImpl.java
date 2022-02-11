package com.user.demo.service.impl;

import com.user.demo.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * 缓存功能实现类
 * @author Stefan
 * @since 2022/1/10
 */
@Service("cacheService")
public class CacheServiceImpl implements CacheService {

    /**
     * String 操作
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String getStringValue(String key) {
        System.out.println(stringRedisTemplate.keys("*"));
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set("NewKeyFromSpringBoot","HiThereBoot");

        return null;
    }
}
