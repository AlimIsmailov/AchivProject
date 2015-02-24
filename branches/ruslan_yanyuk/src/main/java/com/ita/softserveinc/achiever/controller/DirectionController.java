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

import com.ita.softserveinc.achiever.entity.Direction;
import com.ita.softserveinc.achiever.exception.ElementExistsException;
import com.ita.softserveinc.achiever.service.IDirectionService;

/**
 * 
 * @author Andriana
 *
 */
@Controller
public class DirectionController {

	@Autowired
	private IDirectionService directionService;

	@RequestMapping("/directions")
	public String listDirections(Model model) {
		model.addAttribute("direction", new Direction());
		model.addAttribute("directionList", directionService.findAll());
		return "direction/direction";
	}

	@RequestMapping(value = "/addDirection", method = RequestMethod.POST)
	public String addDirection(
			@Valid @ModelAttribute("direction") Direction direction,
			BindingResult result) {
		if (result.hasErrors()) {
			return "redirect:/directions?fail=true";
		}
		try {
			directionService.create(direction);
		} catch (ElementExistsException e) {
			return "redirect:/directions?fail=true";
		}
		return "redirect:/directions?success=true";
	}

	@RequestMapping(value = "/editDirection", method = RequestMethod.POST)
	public String editDirection2(
			@Valid @ModelAttribute("direction") Direction direction,
			BindingResult result) {
		if (result.hasErrors()) {
			return "redirect:/directions?fail=true";
		}
		Direction eDirection = directionService.findById(direction.getId());
		if (eDirection.equals(direction)) {
			return "redirect:/directions";
		}
		eDirection.setName(direction.getName());
		try {
			directionService.update(eDirection);
		} catch (ElementExistsException e) {
			return "redirect:/directions?fail=true";
		}
		return "redirect:/directions?success=true";
	}

	@RequestMapping(value = "/direction/delete/{directionId}", method = RequestMethod.GET)
	public String deleteDirection(@PathVariable("directionId") Long id) {
		Direction direction = directionService.findById(id);
		directionService.delete(direction);
		return "redirect:/directions";
	}

}
