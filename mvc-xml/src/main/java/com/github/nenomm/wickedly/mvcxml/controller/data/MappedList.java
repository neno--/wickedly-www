package com.github.nenomm.wickedly.mvcxml.controller.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MappedList {
	private Map<String, List<Integer>> map = new HashMap();

	public void addList(String key, List<Integer> list) {
		map.put(key, list);
	}

	@Override
	public String toString() {
		return map.toString();
	}
}
