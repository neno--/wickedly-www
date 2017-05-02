package com.github.nenomm.wickedly.oldschool;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloJspServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String text = "From the controller";
		req.setAttribute("text", text);

		RequestDispatcher view = req.getRequestDispatcher("views/firstView.jsp");

		view.forward(req, resp);
	}
}