package com.github.nenomm.wickedly.mvcxml.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloWorldController {
	static final Logger log = LoggerFactory.getLogger(HelloWorldController.class);

	@RequestMapping("/helloWorld")
	public String helloWorld(Model model) {
		model.addAttribute("message", "Hello World!");
		return "springView";
	}

	@RequestMapping(path = "/{day}", method = RequestMethod.GET)
	public void bla(@PathVariable String day) {
		log.info("got pathVar: {}", day);
	}
}
