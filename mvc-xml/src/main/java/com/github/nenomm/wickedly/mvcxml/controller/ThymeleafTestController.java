package com.github.nenomm.wickedly.mvcxml.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(path = "/thyme")
public class ThymeleafTestController {

	@RequestMapping(path = "/test")
	public String helloWorld(Model model, HttpServletRequest req, HttpServletResponse resp) {
		model.addAttribute("message", "Hello World!");
		return "test";
	}
}
