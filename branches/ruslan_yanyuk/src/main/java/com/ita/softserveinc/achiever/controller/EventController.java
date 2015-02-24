package com.ita.softserveinc.achiever.controller;

import java.beans.PropertyEditorSupport;
import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ita.softserveinc.achiever.entity.Event;
import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.entity.Location;
import com.ita.softserveinc.achiever.entity.User;
import com.ita.softserveinc.achiever.exception.ElementExistsException;
import com.ita.softserveinc.achiever.exception.InvalidDateException;
import com.ita.softserveinc.achiever.service.IEventService;
import com.ita.softserveinc.achiever.service.IGroupService;
import com.ita.softserveinc.achiever.service.ILocationService;
import com.ita.softserveinc.achiever.service.IUserService;
import com.ita.softserveinc.achiever.tool.UserFormBean;

@Controller
public class EventController {

	private static final Logger logger = LoggerFactory
			.getLogger(EventController.class);

	@Autowired
	private IEventService eventService;
	@Autowired
	private ILocationService locationService;
	@Autowired
	private IGroupService groupService;
	@Autowired
	private IUserService userService;

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Location.class,
				new PropertyEditorSupport() {
					@Override
					public void setAsText(String text) {
						Location loc = locationService.findById(Long
								.parseLong(text));
						setValue(loc);
					}
				});
	}

	@RequestMapping("/listmanagers")
	public String listManagers(Model model) {
		model.addAttribute("userList",
				userService.findAllByRole("ROLE_MANAGER"));
		return "schedule/listmanagers";
	}

	/**
	 * @param map
	 * @param principal
	 * @return schedules
	 */
	@RequestMapping("/schedule/{userLogin}")
	public String listEvents(@PathVariable("userLogin") String userlogin,
			String submitValue, Model model, Principal principal) {
		DateTime date;
		if (submitValue == null) {
			date = new DateTime();
		} else {
			date = eventService.currentDateTime(submitValue);
		}

		int week = date.getWeekOfWeekyear();
		String login = principal.getName();
		model.addAttribute("event", new Event());
		model.addAttribute("date", date);
		model.addAttribute("location", new Location());
		model.addAttribute("eventList",
				eventService.findByUserCurrentWeek(login, week));
		model.addAttribute("groupList", groupService.findByUser(login));
		model.addAttribute("locationList", locationService.findAll());
		return "schedule/schedule";
	}

	@RequestMapping(value = "/addevent", method = RequestMethod.POST)
	public String addEvent(@ModelAttribute("event") Event event,
			BindingResult result, String date, String day, String startTime,
			String endTime) {

		Group group = event.getGroup();
		Group gr = groupService.findByName(group.getGroupName());
		Location location = new Location();
		Location loc = locationService.findByName(location.getName());
		logger.debug("location: " + location.toString());

		try {
			event.setGroup(gr);
			event.setLocation(loc);
			event.setStartDatetime(eventService.convertDateTime(day, startTime));
			event.setEndDatetime(eventService.convertDateTime(day, endTime));
			event.setDay(day);
			event.setStartTime(startTime);
			event.setEndTime(endTime);
			eventService.create(event);
		} catch (ElementExistsException e) {
			e.printStackTrace();
		}

		return "redirect:/schedule";
	}

	@RequestMapping(value = "/schedule/edit/{eventId}")
	public String editEvent(@PathVariable("eventId") Long id, Model model) {
		model.addAttribute("editableevent", eventService.findById(id));
		return "schedule/schedule";
	}

	@RequestMapping("/schedule/delete/{eventId}")
	public String deleteEvent(@PathVariable("eventId") Long id) {
		Event event = eventService.findById(id);
		eventService.delete(event);
		return "redirect:/schedule";
	}
}
