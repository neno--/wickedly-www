package com.github.nenomm.wickedly.mvcxml.interceptor;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class BadRandomInterceptor extends HandlerInterceptorAdapter {
	static final Logger log = LoggerFactory.getLogger(BadRandomInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,

			Object handler) throws Exception {
		Calendar cal = Calendar.getInstance();
		int second = cal.get(Calendar.SECOND);
		if (second > 50) {
			log.info("Trolling the request...");
			return false;
		}
		return true;
	}
}
