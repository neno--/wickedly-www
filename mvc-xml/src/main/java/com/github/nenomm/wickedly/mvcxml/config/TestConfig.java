package com.github.nenomm.wickedly.mvcxml.config;

import com.github.nenomm.wickedly.mvcxml.service.TestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
	@Bean(name = "fromJavaConfig")
	TestService testService() {
		return new TestService();
	}
}
