package com.github.nenomm.wickedly.mvcxml.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/rest")
public class RestController {

	@RequestMapping("/test")
	@ResponseBody
	String restTest() {
		return "look at me, i am jason!11!";
	}
}
