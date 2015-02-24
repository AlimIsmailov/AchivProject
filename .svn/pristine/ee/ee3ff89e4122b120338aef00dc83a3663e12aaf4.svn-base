package com.ita.softserveinc.achiever.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.ita.softserveinc.achiever.api.EmailVerificationRequest;
import com.ita.softserveinc.achiever.api.PasswordRequest;
import com.ita.softserveinc.achiever.api.UpdateUserRequest;
import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.entity.User;
import com.ita.softserveinc.achiever.exception.ImageUploadException;
import com.ita.softserveinc.achiever.exception.UserNotFoundException;
import com.ita.softserveinc.achiever.service.IGroupService;
import com.ita.softserveinc.achiever.service.IUserService;
import com.ita.softserveinc.achiever.service.IVerificationTokenService;
import com.ita.softserveinc.achiever.tool.UserFormModel;

@Controller
@SessionAttributes(value = { "update", "email", "password" })
@RequestMapping("/profile")
public class ProfileController {

	private static final Logger LOG = LoggerFactory
			.getLogger(ProfileController.class);

	private final IUserService userService;

	private final Validator emailAddressValidator;

	private final Validator passwordValidator;

	private final IGroupService groupService;

	private final IVerificationTokenService verificationTokenService;

	@Autowired
	public ProfileController(
			IUserService userService,
			@Qualifier("emailAddressValidator") Validator emailAddressValidator,
			@Qualifier("passwordValidator") Validator passwordValidator,
			IGroupService groupService,
			IVerificationTokenService verificationTokenService) {

		this.userService = userService;
		this.emailAddressValidator = emailAddressValidator;
		this.passwordValidator = passwordValidator;
		this.groupService = groupService;
		this.verificationTokenService = verificationTokenService;
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		dataBinder.setBindEmptyMultipartFiles(false);
		dataBinder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());

	}

	@RequestMapping(method = RequestMethod.GET, value = "/show/{login}")
	public String showProfilePage(@PathVariable String login, Model model) {

		User user = null;
		try {
			user = userService.findByLogin(login);
		} catch (UserNotFoundException ex) {
			LOG.info(ex.getMessage());
		}

		List<Group> activeGroups = groupService.findCurrentGroups(login);

		UserFormModel userFormModel = new UserFormModel(user, activeGroups);

		model.addAttribute("user", userFormModel);

		return "/user/showprofile";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/edit/{login}")
	public String showEditProfilePage(Principal principal, Model model) {

		User user = null;
		try {
			user = userService.findByLogin(principal.getName());
		} catch (UserNotFoundException ex) {
			LOG.info(ex.getMessage());
		}

		UpdateUserRequest updateUserRequest = new UpdateUserRequest(
				user.getFirstName(), user.getLastName());
		EmailVerificationRequest emailVerificationRequest = new EmailVerificationRequest(
				user.getEmail());
		PasswordRequest passwordRequest = new PasswordRequest();

		model.addAttribute("info", updateUserRequest);
		model.addAttribute("email", emailVerificationRequest);
		model.addAttribute("password", passwordRequest);

		return "user/editprofile";
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST, value = "/edit/{login}", params = "infoForm")
	public String updateUserInfo(
			Principal principal,
			@Valid @ModelAttribute("update") UpdateUserRequest updateUserRequest,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "user/editprofile";
		}

		userService.updateName(principal.getName(), updateUserRequest);

		return "redirect:/profile/show/{login}";
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST, value = "/edit/{login}", params = "emailForm")
	public String updateUserEmail(
			Principal principal,
			@Valid @ModelAttribute("email") EmailVerificationRequest emailVerificationRequest,
			BindingResult bindingResult) {
		emailAddressValidator.validate(emailVerificationRequest, bindingResult);
		if (bindingResult.hasErrors()) {
			return "user/editprofile";
		}

		userService.updateEmail(principal.getName(), emailVerificationRequest);
		verificationTokenService
				.sendEmailVerificationToken(emailVerificationRequest);
		return "user/email-verification";
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST, value = "/edit/{login}", params = "passwordForm")
	public String updateUserPassword(Principal principal,
			@Valid @ModelAttribute("password") PasswordRequest passwordRequest,
			BindingResult bindingResult) {
		passwordValidator.validate(passwordRequest, bindingResult);
		if (bindingResult.hasErrors()) {
			LOG.info(bindingResult.toString());
			return "user/editprofile";
		}

		userService.updatePassword(principal.getName(), passwordRequest);

		return "redirect:/profile/show/{login}";
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST, value = "/edit/{login}", params = "imageForm")
	public String updateUserImage(Principal principal,
			@RequestParam(value = "image", required = false) MultipartFile image) {

		try {

			userService.updateImage(principal.getName(), image);

		} catch (ImageUploadException e) {
			LOG.info(e.getMessage());
			return "user/editprofile";
		}

		return "redirect:/profile/show/{login}";
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/showImage/{login}")
	@ResponseBody
	public byte[] showImage(@PathVariable String login) {
		User user = null;
		try {
			user = userService.findByLogin(login);
		} catch (UserNotFoundException ex) {
			LOG.info(ex.getMessage());
		}
		byte[] image = user.getImage();
		return image != null ? image : null;
	}

}
