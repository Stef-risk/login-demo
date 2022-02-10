package com.user.demo.service;

/**
 * 封装缓存
 * @author Stefan
 * @date 2022/2/10
 */
public interface CacheService {

    /**
     * 获取字符串
     *
     * @param key
     * @return
     */
    String getStringValue(String key);
}
