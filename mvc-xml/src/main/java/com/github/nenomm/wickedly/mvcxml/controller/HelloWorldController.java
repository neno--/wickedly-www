package com.github.nenomm.wickedly.mvcxml.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {
	static final Logger log = LoggerFactory.getLogger(HelloWorldController.class);

	@RequestMapping(path = "/helloWorld", params = "a=trolo")
	public String helloWorld(Model model, HttpServletRequest req, HttpServletResponse resp) {
		model.addAttribute("message", "Hello World!");
		return "springView";
	}

	@RequestMapping(path = "/day/{day}", method = RequestMethod.GET)
	public void bla(@PathVariable String day) {
		log.info("got pathVar: {}", day);
	}

	@RequestMapping(path = "/pets/{petId}")
	@ResponseBody
	public String findPet(@PathVariable String petId, Model model) {
		log.info("Parameter myParam has value of myValue.");
		return petId;
	}
}
