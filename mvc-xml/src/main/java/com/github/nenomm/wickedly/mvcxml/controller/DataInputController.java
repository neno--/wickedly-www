package com.github.nenomm.wickedly.mvcxml.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/dataInput")
public class DataInputController {
	static final Logger log = LoggerFactory.getLogger(DataInputController.class);

	@RequestMapping(path = "/boundToParams")
	@ResponseBody
	public String helloWorld(@RequestParam String param) {
		return param;
	}
}
