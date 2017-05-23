package com.github.nenomm.wickedly.mvcxml.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.nenomm.wickedly.mvcxml.service.TestService;

@Configuration
public class TestConfig {
	@Bean(name = "fromJavaConfig")
	TestService testService() {
		return new TestService();
	}
}
