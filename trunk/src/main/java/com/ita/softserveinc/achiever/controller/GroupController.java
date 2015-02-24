package com.ita.softserveinc.achiever.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.exception.ElementExistsException;
import com.ita.softserveinc.achiever.exception.InvalidDateException;
import com.ita.softserveinc.achiever.service.IApplicationService;
import com.ita.softserveinc.achiever.service.IDirectionService;
import com.ita.softserveinc.achiever.service.IGroupService;
import com.ita.softserveinc.achiever.service.IRoleService;
import com.ita.softserveinc.achiever.service.IUserService;
import com.ita.softserveinc.achiever.tool.GroupFormBean;

/**
 * 
 * @author Andriana
 *
 */
@Controller
public class GroupController {

	@Autowired
	private IGroupService groupService;

	@Autowired
	private IDirectionService directionService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IApplicationService applicationService;

	@RequestMapping("/groups")
	@PreAuthorize (value="isAuthenticated()")
	public String getListGroups(Model model, Principal principal) {
		model.addAttribute("groupFormBean", new GroupFormBean());
		model.addAttribute("directionList", directionService.findAll());
		model.addAttribute("managerList", userService.findManagers());
		if(userService.isOnlyUser(principal.getName())){
			model.addAttribute("futureGroups", groupService.findFutureGroups());
			return "group/futuregroups";
		}
		model.addAttribute("groupList", groupService.getChosenGroups(principal.getName())); 
		return "group/group";
	}

	@RequestMapping(value = "/chooseGroup", method = RequestMethod.POST)
	public String chooseGroups(

	@ModelAttribute("groupFormBean") GroupFormBean groupFormBean,
			BindingResult result, Principal principal, Model model) {
		groupService.getChosenGroups(groupFormBean, principal.getName());
		return "redirect:/groups";
	}

	@RequestMapping(value = "/addGroup", method = RequestMethod.POST)
	public String addGroup(
			@ModelAttribute("groupFormBean") GroupFormBean groupFormBean,
			BindingResult result) {
		try {
			groupService.create(groupService.getGroup(groupFormBean));
		} catch (ElementExistsException e) {
			return "redirect:/groups?groupexists=true";
		} catch (InvalidDateException e) {
			return "redirect:/groups?invaliddate=true";
		}
		return "redirect:/groups?success=true";
	}

	@RequestMapping(value = "/editGroup", method = RequestMethod.POST)
	public String editGroup(
			@Valid @ModelAttribute("groupFormBean") GroupFormBean groupFormBean,
			BindingResult result) {
		try {
			groupService.update(groupService.getGroupToEdit(groupFormBean));
		} catch (ElementExistsException e) {
			return "redirect:/groups?groupexists=true";
		} catch (InvalidDateException e) {
			return "redirect:/groups?invaliddate=true";
		}
		return "redirect:/groups?success=true";
	}

	@RequestMapping("/group/delete/{groupId}")
	public String deleteGroup(@PathVariable("groupId") Long id) {
		Group group = groupService.findById(id);
		groupService.delete(group);
		return "redirect:/groups";
	}

	@RequestMapping("/group/apply/{groupId}")
	public String applyToGroup(@PathVariable("groupId") Long id,
			Principal principal) {
		try {
		groupService.applyToGroup(id, principal.getName());
		} catch (ElementExistsException e) {
			return "redirect:/groups?fail=true";
		}
		return "redirect:/groups?success=true";
	}

	@RequestMapping("/group/{groupId}")
	public String showGroupDetail(Model model,
			@PathVariable("groupId") Long id) {
		Group group = groupService.findById(id);
		model.addAttribute("group", group);
		model.addAttribute(
				"userList",
				userService.findByGroup(group,
						roleService.findByType("ROLE_STUDENT")));
		model.addAttribute(
				"managerList",
				userService.findByGroup(group,
						roleService.findByType("ROLE_MANAGER")));
		return "group/groupInfo";
	}

}
