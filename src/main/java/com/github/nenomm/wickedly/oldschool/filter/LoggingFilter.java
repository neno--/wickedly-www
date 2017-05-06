package com.github.nenomm.wickedly.oldschool.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingFilter implements Filter {
	static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

	private FilterConfig filterConfig;
	private int id = 0;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		log.info("Filter inited.");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		int currentId;

		synchronized (this) {
			currentId = id++;
		}

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		log.info("Got new request #{} for {}, username: {}", currentId, req.getRequestURI(), req.getRemoteUser());

		chain.doFilter(request, response);

		log.info("Sending response for request #{} : {}", currentId, resp.toString());

	}

	@Override
	public void destroy() {
		log.info("Filter destroyed.");
	}
}
