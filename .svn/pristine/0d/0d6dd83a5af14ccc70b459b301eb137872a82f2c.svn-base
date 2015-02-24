package com.ita.softserveinc.achiever.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ita.softserveinc.achiever.entity.Location;
import com.ita.softserveinc.achiever.exception.ElementExistsException;
import com.ita.softserveinc.achiever.exception.InvalidDateException;
import com.ita.softserveinc.achiever.service.ILocationService;


@Controller
public class LocationController {

	@Autowired
	private ILocationService locationService;

	@RequestMapping("/location")
	public String listLocations(Model model) {
		model.addAttribute("location", new Location());
		model.addAttribute("locationList", locationService.findAll());
		return "location/location";
	}

	@RequestMapping(value = "/addLocation", method = RequestMethod.POST)
	public String addLocation(
			@Valid @ModelAttribute("location") Location location,
			BindingResult result) throws InvalidDateException {
		if (result.hasErrors()) {
			return "redirect:/location";
		}
		try {
			locationService.create(location);
		} catch (ElementExistsException e) {
			result.rejectValue("name", "error.location",
					"this location already exists");
			return "redirect:/location";
		}
		return "redirect:/location";
	}

	@RequestMapping(value = "/location/edit/{locationId}")
	public String editLocation(@PathVariable("locationId") Long id,
			Map<String, Object> map) {
		map.put("editableLocation", locationService.findById(id));
		return "location/location";
	}

	@RequestMapping(value = "/location/edit/editLocation", method = RequestMethod.POST)
	public String editLocationPost(
			@Valid @ModelAttribute("editableLocation") Location location,
			BindingResult result) throws InvalidDateException {
		if (result.hasErrors()) {
			return "location/editlocation";
		}
		Location editableLocation = new Location(); 
		try {
			editableLocation.setName(location.getName());
			locationService.update(editableLocation);
		} catch (ElementExistsException e) {
			result.rejectValue("name", "error.location",
					"this location already exists");
			return "location/editlocation";
		}
		return "redirect:/locations";
	}

	@RequestMapping("/location/delete/{locationId}")
	public String deleteLocation(@PathVariable("locationId") Long id) {
		Location location = locationService.findById(id);
		locationService.delete(location);
		return "redirect:/location";
	}

}
