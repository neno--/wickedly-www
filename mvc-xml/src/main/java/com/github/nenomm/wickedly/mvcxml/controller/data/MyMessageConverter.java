package com.github.nenomm.wickedly.mvcxml.controller.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

public class MyMessageConverter extends AbstractHttpMessageConverter {
	private List<MediaType> supportedMediaTypes = new ArrayList<>();

	public MyMessageConverter() {
		supportedMediaTypes.add(MediaType.TEXT_HTML);
	}

	@Override
	protected boolean supports(Class clazz) {
		return (clazz.equals(SimpleComplexResponse.class));
	}

	@Override
	protected Object readInternal(Class clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		return new SimpleComplexResponse("Hello from readInternal");
	}

	@Override
	protected void writeInternal(Object o, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		SimpleComplexResponse res = (SimpleComplexResponse) o;
		outputMessage.getBody().write(res.getGreeting().getBytes());
	}

	@Override
	public List<MediaType> getSupportedMediaTypes() {
		return Collections.unmodifiableList(this.supportedMediaTypes);
	}
}
