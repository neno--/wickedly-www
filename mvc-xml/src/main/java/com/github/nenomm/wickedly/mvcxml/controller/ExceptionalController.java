package com.github.nenomm.wickedly.mvcxml.controller;

import java.util.IllegalFormatCodePointException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExceptionalController {

	@RequestMapping(path = "/boom")
	@ResponseBody
	public String boom() {
		throw new IllegalFormatCodePointException(22);
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public String handleIOException(RuntimeException ex) {
		// return new ResponseEntity<String>("Handled " + ex, HttpStatus.OK);
		return ex.toString();
	}
}
