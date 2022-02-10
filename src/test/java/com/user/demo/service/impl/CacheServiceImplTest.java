package com.user.demo.service.impl;

import com.user.demo.BaseTest;
import com.user.demo.service.CacheService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class CacheServiceImplTest extends BaseTest {

    @Autowired
    private CacheService cacheService;

    @Test
    void getStringValue() {
        cacheService.getStringValue(null);
    }
}