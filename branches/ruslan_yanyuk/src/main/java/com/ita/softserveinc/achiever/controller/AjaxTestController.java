package com.ita.softserveinc.achiever.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxTestController {

	public AjaxTestController() {

	}

	@RequestMapping("/ajax")
	private String ajaxAlert() {
		return "ajaxTest";
	}
	
	
	@RequestMapping("/hello")
	public @ResponseBody String hello(
			@RequestParam(value = "name") String name,
			@RequestParam(value = "gender") String gender,
			@RequestParam(value = "email") String email,
			@RequestParam(value = "phone") String phone,
			@RequestParam(value = "city") String city) {
		System.out.println(name);
		System.out.println(gender);
		System.out.println(email);
		System.out.println(phone);
		System.out.println(city);

		String str = "{\"user\": { \"name\": \"" + name + "\",\"gender\": \""
				+ gender + "\",\"email\": \"" + email + "\",\"phone\": \""
				+ phone + "\",\"city\": \"" + city + "\"}}";
		return str;

	}

}
