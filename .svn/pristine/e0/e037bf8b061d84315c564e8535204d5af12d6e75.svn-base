package com.ita.softserveinc.achiever.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.ita.softserveinc.achiever.api.EmailVerificationRequest;
import com.ita.softserveinc.achiever.exception.UserException;
import com.ita.softserveinc.achiever.service.IUserService;
import com.ita.softserveinc.achiever.service.IVerificationTokenService;
import com.ita.softserveinc.achiever.tool.UserFormBean;

/**
 * 
 * @author Bogdan Rudka
 *
 */
@Controller
public class UserController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);
	@Autowired
	private IUserService userService;

	@Autowired
	@Qualifier("userFormValidator")
	private Validator userFormValidator;

	@Autowired
	@Qualifier("sessionRegistry")
	private SessionRegistry sessionRegistry;

	@Autowired
	private IVerificationTokenService verificationTokenService;

	/*
	 * Initialize the WebDataBinder for data binding from web request parameters
	 * to JavaBean objects.
	 */
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.setBindEmptyMultipartFiles(false);
		dataBinder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
		dataBinder.setValidator(userFormValidator);
	}

	/**
	 * 
	 * @param model
	 * @return User registration form URL
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registrationForm(Model model) {
		logger.info("Show user registration form");
		model.addAttribute("userFormBean", new UserFormBean());
		logger.info("Show user registration form");
		/* model.addAttribute("userList", userService.findAll()); */
		return "user/registration-form";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String registration(@Validated UserFormBean user,
			BindingResult result, Model model) throws UserException {
		if (result.hasErrors()) {
			logger.info("User registration is unsuccessful, the reason is "
					+ result.toString());
			return "user/registration-form";
		}
		logger.info("User password " + user.getUser().getPassword());
		// by default user.active = 0, user.studentCount = 0
		logger.info("User registration is start");
		userService.create(user.getUser());
		logger.info("User registration is successful");
		verificationTokenService
				.sendEmailRegistrationToken(new EmailVerificationRequest(user
						.getUser().getEmail()));
		return "user/email-verification";
	}

	@RequestMapping(value = "/activation")
	private String activation(Model model) {
		return "user/activation";
	}

	@RequestMapping(value = "/online", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody List<String> onlineUsers(Model model) {
		List<Object> principals = sessionRegistry.getAllPrincipals();
		logger.info("All principals" + principals);
		List<String> usersNamesList = new ArrayList<String>();

		for (Object principal : principals) {
			logger.info("usersNamesList" + principal);
			if (principal instanceof org.springframework.security.core.userdetails.User) {
				usersNamesList
						.add(((org.springframework.security.core.userdetails.User) principal)
								.getUsername());
				logger.info("usersNamesList"
						+ ((org.springframework.security.core.userdetails.User) principal)
								.getUsername());
			}
		}
		return usersNamesList;
	}

}
