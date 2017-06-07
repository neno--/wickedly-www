package com.github.nenomm.wickedly.mvcxml.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
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

	// http://localhost:8080/mvc-xml/day/42
	// @RequestAttribute qualifier doesn't seems to work :(
	@RequestMapping(path = "/day/{day}", method = RequestMethod.GET)
	@ResponseBody
	public String bla(@PathVariable String day,
			@RequestAttribute String filterAttribute,
			HttpServletRequest req) {
		log.info("got pathVar: {}, filterAttribute: {}", day, filterAttribute);
		return "filterAttribute: " + filterAttribute;
	}

	// looks like path is not working with @GetMapping
	@RequestMapping(path = "/pets/{petId}")
	@ResponseBody
	public String findPet(@PathVariable String petId, Model model) {
		log.info("Parameter myParam has value of myValue.");
		return petId;
	}

	// this one fails because it needs content type
	// error: Cannot extract parameter (HttpEntity requestEntity): no Content-Type found
	// use filter to add this information if not present
	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "/entity")
	public ResponseEntity<String> handle(HttpEntity<byte[]> requestEntity) throws UnsupportedEncodingException {
		// do something with request header and body

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("X-TROLLED-BY", "trololo");
		return new ResponseEntity<String>("Hello World", responseHeaders, HttpStatus.CREATED);
	}

	@ModelAttribute
	public void thisOneIsAlwaysCalled(Model model) {
		model.addAttribute(new Integer(42));
	}

	// does not work that way - it is called every time. and is throwing exceptions if param is ommited
	/*
	 * @ModelAttribute
	 * public void thisOneIsSometimesCalled(@RequestParam String param, Model model) {
	 * model.addAttribute(new Integer(43));
	 * }
	 */

	// http://localhost:8080/mvc-xml/sessionTest
	@RequestMapping(path = "/sessionTest", method = RequestMethod.GET)
	@ResponseBody
	public String bla1(HttpSession session,
			HttpServletRequest req) {
		synchronized (this) {
			Integer counter = (Integer) session.getAttribute("counter");
			if (counter == null) {
				counter = new Integer(0);
				session.setAttribute("counter", counter);
			}
			else {
				session.setAttribute("counter", counter + 1);
			}
			return "counter: " + counter;
		}
	}

	@RequestMapping("/whatSession")
	@ResponseBody
	public String displayHeaderInfo(@CookieValue(name = "JSESSIONID", required = false) String cookie) {
		return "JSESSIONID: " + cookie;
	}
}
