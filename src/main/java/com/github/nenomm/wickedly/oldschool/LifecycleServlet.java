package com.github.nenomm.wickedly.oldschool;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LifecycleServlet extends HttpServlet {
	static final Logger log = LoggerFactory.getLogger(LifecycleServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		log.info("in doGet method");
		StringBuilder text = new StringBuilder("lifecycle servlet");
		resp.getOutputStream().write(text.toString().getBytes());
	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		log.info("In init method, calling super");
		log.info("Reading init parameter firstParam: {}", servletConfig.getInitParameter("firstParam"));

		/*
		 * this has to be called first, if you want to use servletContext inside init method.
		 */
		super.init(servletConfig);
		log.info("Reading parameter sharedParam: {}", getServletContext().getInitParameter("sharedParam"));

		log.info("In init method, finished with super");

	}

	@Override
	public void destroy() {
		log.info("In destroy method");
	}
}