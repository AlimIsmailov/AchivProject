package com.ita.softserveinc.achiever.controller;


import java.util.HashSet;
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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ita.softserveinc.achiever.entity.Role;
import com.ita.softserveinc.achiever.entity.User;
import com.ita.softserveinc.achiever.service.IRoleService;
import com.ita.softserveinc.achiever.service.IUserService;
import com.ita.softserveinc.achiever.tool.UserListForm;
import com.ita.softserveinc.achiever.tool.UserSearchRequestForm;

/**
 * 
 * @author Ruslan Yanyuk
 */
@Controller
@SessionAttributes(value={"searchRequestForm", "userListForm"})
public class RootController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(RootController.class);

	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;
	
	@RequestMapping(value = "/userManagement", method = RequestMethod.GET)
	public String userManagement(Model model) {
		logger.info("User Management page!");
		Set<Role> roles = new HashSet<Role>();
		UserSearchRequestForm usrSearchForm = new UserSearchRequestForm();
		
		roles.add(roleService.findByType("ROLE_USER"));
		usrSearchForm.setSelectedRoles(roles);
		usrSearchForm.setFirstNameColumn(true);
		usrSearchForm.setLastNameColumn(true);
		

		model.addAttribute("searchRequestForm", usrSearchForm);
		model.addAttribute("userListForm", new UserListForm());
		return "root/search-edit-users";
	}

	@RequestMapping(value = "/searchUsers", method = RequestMethod.POST)
	public String searchUsers(@ModelAttribute("searchRequestForm") UserSearchRequestForm searchRequestForm,	
			@ModelAttribute("userListForm") UserListForm userListForm, Model model) {
		logger.info("Search request in progress...!");
		List<User> userList = null;
		
		logger.info("MYNOTE!!" + searchRequestForm.toString());
		if (searchRequestForm != null)
			if (searchRequestForm.isValidRequest()) {
				userList = userService.findByUserRequest(searchRequestForm);
				userListForm.setUserList(userList);
			}
		
		model.addAttribute("userListForm", userListForm);
		model.addAttribute("searchRequestForm", searchRequestForm);
		return "root/search-edit-users";
	}

	@RequestMapping(value = "/applyChanges", method = RequestMethod.POST)
	public String showView(@ModelAttribute("userListForm") UserListForm userListForm, 
			@ModelAttribute("searchRequestForm") UserSearchRequestForm searchRequestForm,	Model model) {
		logger.info("apply Page !");
		logger.info(userListForm.toString());
		logger.info(userListForm.getModifiedPageContent().toString());
		List<User> userList = null;
		
		if (userListForm != null)
			userService.updateUsersByForm(userListForm);
		
		if (searchRequestForm != null)
			if (searchRequestForm.isValidRequest()) {
				userList = userService.findByUserRequest(searchRequestForm);
				userListForm.setUserList(userList);
			}

		model.addAttribute("searchRequestForm", searchRequestForm);
		model.addAttribute("userListForm", userListForm);
		return "root/search-edit-users";
	}
	
	@RequestMapping(value = "/nextPage", method = RequestMethod.GET)
	public String nextPage(@ModelAttribute("userListForm") UserListForm userListForm, 
			@ModelAttribute("searchRequestForm") UserSearchRequestForm searchRequestForm, Model model) {
		userListForm.nextPage();
		logger.info("NextPage!!!" + searchRequestForm.toString());
		
		model.addAttribute("userListForm", userListForm);
		model.addAttribute("searchRequestForm", searchRequestForm);
		return "root/search-edit-users";
	}
	
	@RequestMapping(value = "/previousPage", method = RequestMethod.GET)
	public String previousPage(@ModelAttribute("userListForm") UserListForm userListForm, 
			@ModelAttribute("searchRequestForm") UserSearchRequestForm searchRequestForm, Model model) {
		userListForm.previousPage();
		
		model.addAttribute("userListForm", userListForm);
		model.addAttribute("searchRequestForm", searchRequestForm);
		return "root/search-edit-users";
	}
}
