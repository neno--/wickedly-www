package com.github.nenomm.wickedly.mvcxml.tweaks;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;

public class CompositePathAwareViewResolver implements ViewResolver, ApplicationContextAware, Ordered {
	private ViewResolver thymeleafViewResolver;
	private ViewResolver jspViewResolver;

	private SpringResourceTemplateResolver thymeleafTemplateResolver;
	private ResourceLoader resourceLoader;
	private int order;

	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		if (checkResource(viewName)) {
			return thymeleafViewResolver.resolveViewName(viewName, locale);
		}
		else {
			return jspViewResolver.resolveViewName(viewName, locale);
		}
	}

	private boolean checkResource(String viewName) {
		return resourceLoader.getResource(
				thymeleafTemplateResolver.getPrefix() + viewName + thymeleafTemplateResolver.getSuffix()).exists();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		resourceLoader = applicationContext;
	}

	public void setThymeleafViewResolver(ViewResolver thymeleafViewResolver) {
		this.thymeleafViewResolver = thymeleafViewResolver;
	}

	public void setJspViewResolver(ViewResolver jspViewResolver) {
		this.jspViewResolver = jspViewResolver;
	}

	public void setThymeleafTemplateResolver(SpringResourceTemplateResolver thymeleafTemplateResolver) {
		this.thymeleafTemplateResolver = thymeleafTemplateResolver;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public int getOrder() {
		return order;
	}
}
