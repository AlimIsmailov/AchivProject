package com.ita.softserveinc.achiever.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ita.softserveinc.achiever.service.IUserService;

/**
 * 
 * @author Bogdan Rudka
 *
 */

@Controller
public class LoginController {

	@Autowired
	private IUserService userService;

	@PreAuthorize("isAnonymous()")
	@RequestMapping(value = "/login")
	public String login() {
		return "user/login";
	}

	@RequestMapping(value = "/login/failed")
	public String loginFailed(Model model) {
		model.addAttribute("error", true);
		return "user/login";
	}

	@RequestMapping(value = "/403")
	public String permissionDenied() {
		return "error/403";
	}
	
	@RequestMapping(value = "/forgotPassword")
	public String forgotPassword() {
		return "user/forgot-password";
	}
}
