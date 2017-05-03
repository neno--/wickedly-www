package com.github.nenomm.wickedly.oldschool.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyListener implements ServletContextListener {
	static final Logger log = LoggerFactory.getLogger(MyListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.info("Listener reading parameter sharedParam: {}", sce.getServletContext().getInitParameter("sharedParam"));
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("Listener context destroyed");
	}
}
