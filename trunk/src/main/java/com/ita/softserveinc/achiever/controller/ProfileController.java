package com.ita.softserveinc.achiever.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
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

import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.entity.User;
import com.ita.softserveinc.achiever.exception.ImageUploadException;
import com.ita.softserveinc.achiever.service.IGroupService;
import com.ita.softserveinc.achiever.service.IUserService;
import com.ita.softserveinc.achiever.tool.UserFormBean;

@Controller
@RequestMapping("/users/profile")
@SessionAttributes("userForm")
public class ProfileController {

	private static final Logger lOG = LoggerFactory
			.getLogger(ProfileController.class);

	private final IUserService userService;

	private final Validator userFormValidator;
	
	private final IGroupService groupService;

	@Autowired
	public ProfileController(IUserService userService,
			@Qualifier("userFormValidator") Validator userFormValidator, IGroupService groupService) {
		this.userService = userService;
		this.userFormValidator = userFormValidator;
		this.groupService = groupService;
	}

	/*
	 * Initialize the WebDataBinder for data binding from web request parameters
	 * to JavaBean objects.
	 */
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		dataBinder.setDisallowedFields("id", "login", "roles");
		dataBinder.setBindEmptyMultipartFiles(false);
		dataBinder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
		dataBinder.setValidator(userFormValidator);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/show/{login}")
	public String showProfilePage(@PathVariable("login") String login,
			Model model) {
		UserFormBean userForm = new UserFormBean(
				this.userService.findByLogin(login));
		List<Group> currentGroups = this.groupService.findCurrentGroups(login);
		model.addAttribute("userForm", userForm);
		model.addAttribute("currentGroups", currentGroups);
		
		return "/user/showprofile";
	}

	
	@PreAuthorize(value = "#userForm.user.login == principal.username or hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/edit/{login}")
	public String showEditProfilePage(@ModelAttribute("userForm") UserFormBean userForm) {
		return "/user/editprofile";
	}

	@PreAuthorize(value = "#userForm.user.login == principal.username or hasRole('ROLE_ADMIN')")
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST, value = "/edit/{login}")
	public String editProfileFromForm(
			@Validated @ModelAttribute("userForm") UserFormBean userForm,
			BindingResult bindingResult,
			@RequestParam(value = "image", required = false) MultipartFile image) {
		if (bindingResult.hasErrors()) {
			return "/user/editprofile";
		}
		try {
			if (!image.isEmpty()) {
				validateImage(image);
				saveImage(userForm.getUser(), image);
			}
		} catch (ImageUploadException e) {
			bindingResult.reject(e.getMessage());
			return "/user/editprofile";
		}
		userService.update(userForm.getUser());
		return "redirect:/users/profile/show/{login}";
	}

	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/showImage/{login}")
	@ResponseBody
	public byte[] showImage(@PathVariable("login") String login) {
		User user = userService.findByLogin(login);
		byte[] image = user.getImage();
		return image != null ? image : null;
	}
	
	private void validateImage(MultipartFile image) {
		if (!image.getContentType().equals("image/jpeg")) {
			throw new ImageUploadException("Only JPG images accepted");
		}
	}

	private void saveImage(User user, MultipartFile image) {
		try {

			byte[] uploadImage = image.getBytes();
			user.setImage(uploadImage);

		} catch (Exception e) {
			throw new ImageUploadException("Unable to save image", e);
		}

	}

	

}
