package com.ita.softserveinc.achiever.controller;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ita.softserveinc.achiever.entity.Role;
import com.ita.softserveinc.achiever.entity.User;
import com.ita.softserveinc.achiever.service.IRoleService;
import com.ita.softserveinc.achiever.service.IUserService;
import com.ita.softserveinc.achiever.tool.UserListForm;
import com.ita.softserveinc.achiever.tool.UserSearchRequest;

/**
 * 
 * @author Ruslan Yanyuk
 */
@Controller
public class RootController {

	private static final Logger logger = LoggerFactory
			.getLogger(RootController.class);

	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;

	@RequestMapping(value = "/userManagement", method = RequestMethod.GET)
	public String createManagerPage(Model model) {
		logger.info("Createmanager page !");

		model.addAttribute("searchRequestForm", new UserSearchRequest());
		model.addAttribute("userListForm", new UserListForm());
		return "root/search-edit-users";
	}

	@RequestMapping(value = "/searchUsers", method = RequestMethod.POST)
	public String searchUsers(
			@ModelAttribute("searchRequestForm") UserSearchRequest searchRequestForm,
			Model model) {
		logger.info("Search request in progress...!");
		model.addAttribute("searchRequestForm", new UserSearchRequest());
		UserListForm userListForm = new UserListForm();;
		
		if (!searchRequestForm.isValidRequest()) {
			logger.debug("Request is not valid!");
			model.addAttribute("userListForm", new UserListForm());
			return "root/search-edit-users";
		}
		
		if (searchRequestForm.isAllRolesSected()) {
			logger.debug("All roles are selected!");
			userListForm.setUserList(userService.findAll());
			model.addAttribute("userListForm", userListForm);
			return "root/search-edit-users";
		}
		
		if (!searchRequestForm.getLoginField().equals(""))
		{
			logger.debug("Query by login field!");
			List<User> userList = new LinkedList<User>();
			userList.add(userService.findByLogin(searchRequestForm.getLoginField()));
			userListForm.setUserList(userList);
			model.addAttribute("userListForm", userListForm);
			return "root/search-edit-users";
		}

		model.addAttribute("userListForm", new UserListForm());
		return "root/search-edit-users";
	}

	@RequestMapping(value = "/applyChanges", method = RequestMethod.POST)
	public String showView(
			@ModelAttribute("userListForm") UserListForm userListForm,
			Model model) {
		logger.info("apply Page !");
		
		if (userListForm.getUserList() != null) {
		for (User user : userListForm.getUserList()) {
			Set<Role> roleSet = new HashSet<Role>();
			User userFromDB = userService.findById(user.getId());
			
			if (userFromDB == null)
				continue;
			if (user.getRoles() == null)
				continue;
			
			for (Role role : user.getRoles()) {
				Role roleFromDB;
				roleFromDB = roleService.findByType(role.getType());
				if (roleFromDB == null)
					continue;
				roleSet.add(roleFromDB);
			}
			if (roleSet.size() != 0) {
				userFromDB.setRoles(roleSet);
				userService.update(userFromDB);
			}
		}
		}

		model.addAttribute("searchRequestForm", new UserSearchRequest());
		model.addAttribute("userListForm", userListForm);

		return "root/search-edit-users";
	}
}
