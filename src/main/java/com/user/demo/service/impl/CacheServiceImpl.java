package com.user.demo.service.impl;

import com.user.demo.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service("cacheService")
public class CacheServiceImpl implements CacheService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String getStringValue(String key) {
        System.out.println(stringRedisTemplate.keys("*"));
        return null;
    }
}
