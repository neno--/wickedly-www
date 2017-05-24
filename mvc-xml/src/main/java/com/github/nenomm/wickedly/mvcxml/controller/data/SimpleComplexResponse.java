package com.github.nenomm.wickedly.mvcxml.controller.data;

public class SimpleComplexResponse {
	private boolean simple = true;
	private boolean complex = true;
	private String greeting = "HAI";

	public boolean isSimple() {
		return simple;
	}

	public boolean isComplex() {
		return complex;
	}

	public String getGreeting() {
		return greeting;
	}
}
