package com.ita.softserveinc.achiever.tool;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class PassTestFormBean {

	private long id;

	private Map<String, List<String>> testsData = new LinkedHashMap<String, List<String>>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Map<String, List<String>> getTestsData() {
		return testsData;
	}

	public void setTestsData(Map<String, List<String>> testsData) {
		this.testsData = testsData;
	}

}
