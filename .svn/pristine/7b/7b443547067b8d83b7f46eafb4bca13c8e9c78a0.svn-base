package com.ita.softserveinc.achiever.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ita.softserveinc.achiever.dao.IApplicationDao;
import com.ita.softserveinc.achiever.entity.Application;
import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.entity.User;
import com.ita.softserveinc.achiever.exception.ElementExistsException;
import com.ita.softserveinc.achiever.exception.InvalidDateException;
import com.ita.softserveinc.achiever.service.IApplicationService;
import com.ita.softserveinc.achiever.service.IGroupService;
import com.ita.softserveinc.achiever.service.IRoleService;
import com.ita.softserveinc.achiever.service.IUserService;

/**
 * 
 * @author Andriana
 *
 */
@Controller
public class ApplicationController {
	
	private static final short APPLICATION_CONFIRMED=1;
	private static final short APPLICATION_DELETED=2;

	@Autowired
	private IApplicationService applicationService;

	@Autowired
	private IGroupService groupService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IApplicationDao applicationDao;

	@RequestMapping("/applications")
	public String listApplications(Model model, Principal principal) {
		model.addAttribute("applicationList",
				applicationService.findByManagerLogin(principal.getName()));
		return "application/application";
	}

	@RequestMapping(value = "/application/confirm/{applicationId}")
	public String confirmApplication(@PathVariable("applicationId") Long id, RedirectAttributes redirectAttributes) {
		Application application = applicationService.findById(id);
		Group group = application.getGroup();
		User user = application.getUser();
		user = userService.findByLogin(user.getLogin());
		group.addUser(user);
		try {
			groupService.update(group);
		} catch (ElementExistsException e) {
		} catch (InvalidDateException e) {
		}
		application.setStatus(APPLICATION_CONFIRMED);
		applicationDao.update(application);
		applicationService.update(application);
		redirectAttributes.addFlashAttribute("confirmed", true);
		return "redirect:/applications";
	}

	@RequestMapping(value = "/application/delete/{applicationId}")
	public String deleteApplication(@PathVariable("applicationId") Long id,
			Model model, RedirectAttributes redirectAttributes) {
		Application application = applicationService.findById(id);
		application.setStatus(APPLICATION_DELETED);
		applicationService.update(application);
		redirectAttributes.addFlashAttribute("deleted", true);
		return "redirect:/applications";
	}

}
