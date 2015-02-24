package com.ita.softserveinc.achiever.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ita.softserveinc.achiever.entity.Direction;
import com.ita.softserveinc.achiever.exception.ElementExistsException;
import com.ita.softserveinc.achiever.service.IDirectionService;
import com.ita.softserveinc.achiever.service.IGroupService;

/**
 * 
 * @author Andriana
 *
 */
@Controller
public class DirectionController {

	@Autowired
	private IDirectionService directionService;
	
	@Autowired
	private IGroupService groupService;
	
	@RequestMapping("/datep")
	public String showD(){
		return "datepickertest";
	}

	@RequestMapping ("/directions/{directionId}")
	public String showDirectionDetail(Model model,
			@PathVariable("directionId") Long id) {
		model.addAttribute("direction", directionService.findById(id));
		return "direction/directionInfo";
	}

	@RequestMapping("/directions")
	public String listDirections(Model model) {
		model.addAttribute("direction", new Direction());
		model.addAttribute("directionList", directionService.findAll());
		model.addAttribute("resultsOnPage", groupService.getResultsOnPageCount());
		return "direction/direction";
	}

	@RequestMapping(value = "/addDirection", method = RequestMethod.POST)
	public String addDirection(
			@Valid @ModelAttribute("direction") Direction direction,
			BindingResult result, RedirectAttributes redirectAttributes) {
		try {
			directionService.create(direction);
		} catch (ElementExistsException e) {
			redirectAttributes.addFlashAttribute("fail", true);
			return "redirect:/directions";
		}
		redirectAttributes.addFlashAttribute("success", true);
		return "redirect:/directions";
	}

	@RequestMapping(value = "/editDirection", method = RequestMethod.POST)
	public String editDirection(
			@Valid @ModelAttribute("direction") Direction direction,
			BindingResult result, RedirectAttributes redirectAttributes) {
		Direction eDirection = directionService.findById(direction.getId());
		if (eDirection.equals(direction)) {
			return "redirect:/directions";
		}
		eDirection.setName(direction.getName());
		try {
			directionService.update(eDirection);
		} catch (ElementExistsException e) {
			redirectAttributes.addFlashAttribute("fail", true);
			return "redirect:/directions";
		}
		redirectAttributes.addFlashAttribute("success", true);
		return "redirect:/directions";
	}

	@RequestMapping(value = "/direction/delete/{directionId}", method = RequestMethod.GET)
	public String deleteDirection(@PathVariable("directionId") Long id) {
		Direction direction = directionService.findById(id);
		directionService.delete(direction);
		return "redirect:/directions";
	}

}
