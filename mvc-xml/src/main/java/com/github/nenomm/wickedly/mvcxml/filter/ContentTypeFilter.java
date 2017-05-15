package com.github.nenomm.wickedly.mvcxml.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

public class ContentTypeFilter implements Filter {
	private static final Logger log = LoggerFactory.getLogger(ContentTypeFilter.class);
	private static final String CONTENT_TYPE_VALUE = "text/html; charset=utf-8";
	public static final String FILTER_ATTRIBUTE_NAME = "filterAttribute";

	private FilterConfig filterConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		log.info("Filter inited.");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		final HttpServletRequest httpRequest = (HttpServletRequest) request;

		httpRequest.setAttribute(FILTER_ATTRIBUTE_NAME, new Integer(22));

		HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(httpRequest) {
			@Override
			public String getHeader(String name) {
				final String value = httpRequest.getParameter(name);
				if (value != null) {
					return value;
				}
				else if (name.equalsIgnoreCase(HttpHeaders.CONTENT_TYPE)) {
					return CONTENT_TYPE_VALUE;
				}
				return super.getHeader(name);
			}

			@Override
			public Enumeration<String> getHeaderNames() {
				List<String> names = Collections.list(httpRequest.getHeaderNames());
				names.add(HttpHeaders.CONTENT_TYPE);
				return Collections.enumeration(names);
			}

			@Override
			public Enumeration<String> getHeaders(String name) {
				Enumeration<String> result = httpRequest.getHeaders(name);
				if (name.equalsIgnoreCase(HttpHeaders.CONTENT_TYPE)) {
					List<String> headers = new ArrayList<>();
					headers.add(CONTENT_TYPE_VALUE);
					return Collections.enumeration(headers);
				}
				return result;
			}
		};

		chain.doFilter(wrapper, response);
	}

	@Override
	public void destroy() {
		// nothing to do
	}
}
