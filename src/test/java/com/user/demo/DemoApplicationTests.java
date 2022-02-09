package com.user.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(locations = ("classpath:spring/spring-config.xml"))
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

}
