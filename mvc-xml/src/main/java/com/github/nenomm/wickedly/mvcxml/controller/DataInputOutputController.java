package com.github.nenomm.wickedly.mvcxml.controller;

import java.beans.PropertyEditorSupport;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import com.github.nenomm.wickedly.mvcxml.controller.data.MappedList;
import com.github.nenomm.wickedly.mvcxml.controller.data.SimpleComplexResponse;

@Controller
@RequestMapping(path = "/dataInput")
public class DataInputOutputController {
	static final Logger log = LoggerFactory.getLogger(DataInputOutputController.class);

	@RequestMapping(path = "/boundToParams")
	@ResponseBody
	public String helloWorld(@RequestParam String param) {
		return param;
	}

	// http://localhost:8080/mvc-xml/dataInput/parseCustomData?specialParam=[key1,1,2,3][key2,3,4,65]
	@RequestMapping(path = "/parseCustomData")
	@ResponseBody
	public String helloWorld(@RequestParam MappedList specialParam) {
		return specialParam.toString();
	}

	@InitBinder
	private void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(MappedList.class, new PropertyEditorSupport() {
			public void setAsText(String text) {
				// [key1,1,2,3,4][key2,3,4,3]

				MappedList result = new MappedList();

				int index;
				String[] tokens;
				List<Integer> list = new ArrayList<>();

				// fixme: parses just first key/value pair.
				do {
					index = text.indexOf(']', 1);
					tokens = text.substring(1, index).split(",");
					list.clear();
					for (int i = 1; i < tokens.length; i++) {
						list.add(Integer.parseInt(tokens[i]));
					}
					result.addList(tokens[0], list);

				} while (index == (text.length() - 1));

				setValue(result);
			}
		});
	}

	@RequestMapping("/generateUri/{thisOne}")
	@ResponseBody
	public String generateUri(@RequestParam String perica) {
		UriComponents uriComponents = MvcUriComponentsBuilder
				.fromMethodName(this.getClass(), "generateUri", "iAmPerica").buildAndExpand("thatOne");

		URI uri = uriComponents.encode().toUri();

		return "URI for this method is: " + uri.toString();
	}

	@RequestMapping("/customResponse")
	@ResponseBody
	SimpleComplexResponse createResponse(HttpServletRequest req) {
		log.info("Requested representation is: {}", req.getHeader(HttpHeaders.ACCEPT));
		return new SimpleComplexResponse("HAI from createResponse");
	}

	@RequestMapping("/customResponse/httpEntity")
	@ResponseBody
	HttpEntity<SimpleComplexResponse> createResponseHttpEntity(HttpServletRequest req) {
		log.info("Requested representation is: {}", req.getHeader(HttpHeaders.ACCEPT));
		return new HttpEntity<SimpleComplexResponse>(new SimpleComplexResponse("HAI from createResponseHttpEntity"));
	}

	@RequestMapping("/customResponse/responseEntity")
	@ResponseBody
	ResponseEntity<SimpleComplexResponse> createResponseResponseEntity(HttpServletRequest req) {
		log.info("Requested representation is: {}", req.getHeader(HttpHeaders.ACCEPT));
		return new ResponseEntity<SimpleComplexResponse>(
				new SimpleComplexResponse("HAI from createResponseResponseEntity"),
				HttpStatus.CREATED);
	}

	@RequestMapping("/customRequest")
	@ResponseBody
	String createRequest(HttpEntity<SimpleComplexResponse> req) {
		SimpleComplexResponse body = req.getBody();
		return "AOK " + body.getGreeting();
	}

}
