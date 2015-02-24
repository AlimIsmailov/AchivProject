package com.ita.softserveinc.achiever.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.service.IGroupService;
import com.ita.softserveinc.achiever.tool.UserRatingDto;

@Controller
public class AjaxTestController {
	
	@Autowired
	private IGroupService groupService;

	public AjaxTestController() {

	}

	@RequestMapping("/ajax")
	private String ajaxAlert() {
		return "ajaxTest";
	}
	
	@RequestMapping("/ajaxTable")
	public String ajaxTestTable(){
		//System.err.println("Login: "+principal.getName());
		return "ajaxTestTable";
	}
	
	@RequestMapping("/helloTable")
	public @ResponseBody List<Group> helloTable(){
		List<Group> groups = groupService.findAll();
		return groups;
		/*List<String> groupNames = new ArrayList<String>();
		for (Group group: groups){
			groupNames.add(group.getGroupName());
		}
		return groupNames;*/
	}
	
	@RequestMapping("/helloTable2")
	public @ResponseBody List<Group> helloTable2(
			@RequestParam(value="future") boolean future,
			@RequestParam(value="current") boolean current,
			@RequestParam(value="finished") boolean finished,
			Principal principal){
		return groupService.getChosenGroups2(future, current, finished,principal.getName() );
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
