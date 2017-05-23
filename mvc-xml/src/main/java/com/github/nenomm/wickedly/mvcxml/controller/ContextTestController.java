package com.github.nenomm.wickedly.mvcxml.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.github.nenomm.wickedly.mvcxml.service.TestService;

@Controller
public class ContextTestController {
	static final Logger log = LoggerFactory.getLogger(ContextTestController.class);

	@Resource(name = "fromJavaConfig")
	TestService testService;

	// http://localhost:8080/mvc-xml/ctx
	@RequestMapping(path = "/ctx", method = RequestMethod.GET)
	@ResponseBody
	public String findOutCotextInfo(HttpServletRequest req) {
		ApplicationContext ctx = RequestContextUtils.findWebApplicationContext(req);

		List<String> contexts = new ArrayList<>();
		do {
			contexts.add(ctx.getDisplayName() + "\n");
		} while ((ctx = ctx.getParent()) != null);

		/*
		 * displays only servlet web app context
		 * [WebApplicationContext for namespace 'myMvcServlet-servlet'] if used with vanilla conf
		 *
		 * if used with root context:
		 * Contexts (child to parent):
		 * [WebApplicationContext for namespace 'myMvcServlet-servlet'
		 * , Root WebApplicationContext
		 *
		 */

		log.info("testService real bean name is: {}", testService.getBeanName());

		return "Contexts (child to parent):\n" + contexts;

	}
}
