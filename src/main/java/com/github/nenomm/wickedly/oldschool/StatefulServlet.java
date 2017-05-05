package com.github.nenomm.wickedly.oldschool;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StatefulServlet extends HttpServlet {
	static final Logger log = LoggerFactory.getLogger(StatefulServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession httpSession = req.getSession();

		if (httpSession.isNew()) {
			log.info("New session created: {}", httpSession.getId());
		}
		else {
			log.info("Session already exists: {}", httpSession.getId());
		}

		resp.getOutputStream().write(httpSession.getId().getBytes());
	}
}