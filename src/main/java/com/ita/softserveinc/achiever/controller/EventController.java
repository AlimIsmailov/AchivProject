package com.ita.softserveinc.achiever.controller;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ita.softserveinc.achiever.entity.Event;
import com.ita.softserveinc.achiever.entity.Location;
import com.ita.softserveinc.achiever.exception.ElementExistsException;
import com.ita.softserveinc.achiever.exception.InvalidDateException;
import com.ita.softserveinc.achiever.service.EventServiceImpl;
import com.ita.softserveinc.achiever.service.IEventService;
import com.ita.softserveinc.achiever.service.IGroupService;
import com.ita.softserveinc.achiever.service.ILocationService;
import com.ita.softserveinc.achiever.service.IUserService;
import com.ita.softserveinc.achiever.tool.Consts;
import com.ita.softserveinc.achiever.tool.DateParser;
import com.ita.softserveinc.achiever.tool.EventFormBean;
import com.ita.softserveinc.achiever.tool.FileUploaderDownloader;

@Controller
public class EventController {

	@Autowired
	private IEventService eventService;
	@Autowired
	private ILocationService locationService;
	@Autowired
	private IGroupService groupService;
	@Autowired
	private IUserService userService;
	@Autowired
	private FileUploaderDownloader fileUploaderDownloader;
	@Autowired
	private DateParser dateParser;
	
	private static final Logger logger = LoggerFactory
			.getLogger(EventServiceImpl.class);

	/* 
	 * Method create new custom editor for Location class and override SetAsText method.
	 * This operation allow set received location to FormBean
	 */
	
	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Location.class, new PropertyEditorSupport(
				locationService) {
			@Override
			public void setAsText(String text) {
				Location loc = locationService.findById(Long.parseLong(text));
				setValue(loc);
			}
		});
	}

	/* 
	 * Method has all data for schedule view 
	 */
	@RequestMapping("/schedule")
	public String listEvents(Model model, @ModelAttribute("week") String week,
			@ModelAttribute("scheduleParameter") String login, Principal principal) {

		DateTime date = eventService.currentDateTime(week);
		model.addAttribute("eventFormBean", new EventFormBean());
		model.addAttribute("date", date);
		model.addAttribute("currentweek", date.getWeekOfWeekyear());
		model.addAttribute("eventList",
				eventService.findCurrentWeekEvents(date, eventService.isLoginEmpty(login, principal)));
		model.addAttribute("groupList", eventService.findGroupForJsp(login));
		model.addAttribute("locationList",
				locationService.findLocationForJsp(login));
		model.addAttribute("scheduleParameter", eventService.isLoginEmpty(login, principal));
		model.addAttribute("week", week);
		return "schedule/schedule";
	}

	/* 
	 * Method for admin page with selection of schedule by subject
	 */
	
	@RequestMapping("/adminschedule")
	public String adminSchedule(Model model) {
		return "schedule/adminschedule";
	}

	/* 
	 * Method has list of groups 
	 */
	@RequestMapping("/listgroups")
	public String listGroups(Model model) {
		model.addAttribute("groupList", groupService.findAll());
		model.addAttribute("week", new DateTime().getWeekOfWeekyear());
		return "schedule/listgroups";
	}

	/* 
	 * Method list of managers 
	 */
	@RequestMapping("/listmanagers")
	public String listManagers(Model model) {
		model.addAttribute("userList",
				userService.findAllByRole("ROLE_MANAGER"));
		model.addAttribute("week", new DateTime().getWeekOfWeekyear());
		return "schedule/listmanagers";
	}

	/* 
	 * GET Method list of locations 
	 */
	@RequestMapping("/listlocations")
	public String listLocations(Model model) {

		model.addAttribute("locationList", locationService.findAll());
		model.addAttribute("week", new DateTime().getWeekOfWeekyear());
		return "schedule/listlocations";
	}

	/* 
	 * Method for change week on JSP. Receive week number 
	 * and add return it, as attribute to schedule view
	 */
	@RequestMapping(value = "/{week}", method = RequestMethod.POST)
	public String changeWeek(@PathVariable @ModelAttribute("week") String week,
			@ModelAttribute("scheduleParameter") String login, Model model) {

		model.addAttribute("week", week);
		model.addAttribute("scheduleParameter", login);
		return "redirect:/schedule";
	}

	/* 
	 * Method for create new Event 
	 */
	@RequestMapping(value = "/addevent", method = RequestMethod.POST)
	public String addEvent(@ModelAttribute("week") String week,
			@ModelAttribute("scheduleParameter") String login,
			@ModelAttribute("eventFormBean") EventFormBean eventFormBean,
			Model model, @ModelAttribute("date") String datetime)
			throws InvalidDateException, ElementExistsException {

		eventService.create(eventService.getEvent(eventFormBean));
		model.addAttribute("scheduleParameter", login);
		model.addAttribute("week", week);

		return "redirect:/schedule";
	}

	/* 
	 * Method for update exist Event 
	 */
	@RequestMapping(value = "/editevent", method = RequestMethod.POST)
	public String editEvent(@ModelAttribute("week") String week,
			@ModelAttribute("scheduleParameter") String login,
			@ModelAttribute("eventFormBean") EventFormBean eventFormBean,
			Model model, @ModelAttribute("date") String datetime)
			throws InvalidDateException, ElementExistsException {

		logger.info("edit week is: " + week);
		DateTime date = DateTime.parse(datetime,
				DateTimeFormat.forPattern(Consts.FULL_DATETIME_PATTERN));
		eventService.update(eventService.getEditableEvent(eventFormBean, date));
		model.addAttribute("scheduleParameter", login);
		model.addAttribute("week", week);

		return "redirect:/schedule";
	}

	/* 
	 * Method for delete Event from database 
	 */
	@RequestMapping(value = "/schedule/delete/{eventId}")
	public String deleteEvent(@ModelAttribute("scheduleParameter") String login,
			@ModelAttribute("week") String week,
			@PathVariable("eventId") Long id, Model model) {

		Event event = eventService.findById(id);
		eventService.delete(event);
		model.addAttribute("week", week);
		model.addAttribute("scheduleParameter", login);
		return "redirect:/schedule";
	}

	/* 
	 * Upload CSV file on server 
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String uploadFile(@RequestParam("file") MultipartFile file)
			throws IOException {

		String name = file.getOriginalFilename();
		fileUploaderDownloader.uploadFile(name, file);
		eventService.writeEventsToDb();
		return "redirect:/adminschedule";
	}

	/* 
	 * Download CSV file with all Events from server  
	 */
	@RequestMapping(value = "/download", method = RequestMethod.POST)
	public void downloadzFile(HttpServletResponse response) throws IOException {

		List<Event> events = eventService.findAll();
		eventService.readEventsFromDb(events);
		fileUploaderDownloader.downloadFile(response);
	}

	/* 
	 * Download CSV file with all events for particular
	 * subject (location/group or user) from server  
	 */
	@RequestMapping(value = "/downloadvar", method = RequestMethod.POST)
	public void downloadBy(HttpServletResponse response,
			@ModelAttribute("scheduleParameter") String scheduleParameter) throws IOException {

		List<Event> events = eventService.findEventsForDownload(scheduleParameter);
		eventService.readEventsFromDb(events);
		fileUploaderDownloader.downloadFile(response);
	}

	/* 
	 * Download CSV file with events between for particular
	 * subject (location/group or user) from server  
	 */
	@RequestMapping(value = "/downloadFile", method = RequestMethod.POST)
	public void downloadFile(HttpServletResponse response,
			@ModelAttribute("scheduleParameter") String scheduleParameter, String start,
			String end, Model model) throws IOException {

		DateTime startDate = DateTime.parse(start,
				DateTimeFormat.forPattern(Consts.DATETIME_PATTERN_WITHOUT_TIME));
		DateTime endDate = DateTime.parse(end,
				DateTimeFormat.forPattern(Consts.DATETIME_PATTERN_WITHOUT_TIME));
		List<Event> events = eventService.findEventsForDownload(scheduleParameter,
				startDate, endDate);
		eventService.readEventsFromDb(events);
		fileUploaderDownloader.downloadFile(response);

	}
}
