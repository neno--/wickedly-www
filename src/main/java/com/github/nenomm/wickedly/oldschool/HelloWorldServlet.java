package com.github.nenomm.wickedly.oldschool;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		StringBuilder text = new StringBuilder();

		ServletContext servletContext = getServletContext();

		text = text.append("(From request) Context Path: ").append(req.getContextPath()).append("\n");
		text = text.append("(From request) Servlet Path: ").append(req.getServletPath()).append("\n");
		text = text.append("(From request) Path Info: ").append(req.getPathInfo()).append("\n");
		text = text.append("(From request) Path Translated: ").append(req.getPathTranslated()).append("\n");
		text = text.append("(From servlet context) Path Translated (/example): ")
				.append(servletContext.getRealPath("/example")).append("\n");
		text = text.append("(From servlet context) Path Translated (example): ")
				.append(servletContext.getRealPath("example")).append("\n");
		text = text.append("(From servlet context) javax.servlet.context.tempdir attribute: ")
				.append(servletContext.getAttribute("javax.servlet.context.tempdir")).append("\n");

		resp.getOutputStream().write(text.toString().getBytes());
	}
}