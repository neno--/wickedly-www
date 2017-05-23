package com.github.nenomm.wickedly.mvcxml.service;

import org.springframework.beans.factory.BeanNameAware;

public class TestService implements BeanNameAware {
	private String beanName;

	public String getBeanName() {
		return beanName;
	}

	@Override
	public void setBeanName(String name) {
		this.beanName = name;
	}
}
