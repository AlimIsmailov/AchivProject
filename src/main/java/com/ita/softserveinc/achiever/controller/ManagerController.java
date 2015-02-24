package com.ita.softserveinc.achiever.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ita.softserveinc.achiever.entity.User;
import com.ita.softserveinc.achiever.paging.PagingCounter;
import com.ita.softserveinc.achiever.service.IDirectionService;
import com.ita.softserveinc.achiever.service.IGroupService;
import com.ita.softserveinc.achiever.service.IUserService;

@Controller
public class ManagerController extends PagingCounter {
	/**
	 * @author Alim Ismailov
	 */

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);
	@Autowired
	private IUserService userService;

	@Autowired
	private IGroupService groupService;

	@Autowired
	private IDirectionService directionService;

	@RequestMapping(value = "useractivation", method = RequestMethod.GET)
	public String inactiveUsers(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("userList",
				userService.findByActiveStatusResultOnPage(fieldsOnPageMax));
		model.addAttribute("resultsOnPage",
				groupService.getResultsOnPageCount());
		return "user/useractivation";
	}

	@RequestMapping(value = "{result}", method = RequestMethod.GET)
	public String showOnPage5elem(@PathVariable("result") int fieldsOnPage,
			Model model) {
		fieldsOnPageMax = fieldsOnPage;
		pagingCount = fieldsOnPage;
		pagePrevAndNext = fieldsOnPage;
		model.addAttribute("userList",
				userService.findByActiveStatusResultOnPage(fieldsOnPageMax));
		model.addAttribute("resultsOnPage",
				groupService.getResultsOnPageCount());
		return "user/useractivation";
	}

	@RequestMapping(value = "prev", method = RequestMethod.GET)
	public String prevPage(Model model) {
		model.addAttribute(
				"userList",
				userService.findByActiveStatusPagination(prevCount, fieldsOnPageMax));
		model.addAttribute("resultsOnPage",
				groupService.getResultsOnPageCount());
		prevCount = prevCount - pagePrevAndNext;
		return "user/useractivation";
	}

	@RequestMapping(value = "page/{page}", method = RequestMethod.GET)
	public String paging(@PathVariable("page") int page, Model model) {
		int paggg = page - 1;
		pagingCount = fieldsOnPageMax * paggg;
		model.addAttribute("userList", userService
				.findByActiveStatusPagination(pagingCount, fieldsOnPageMax));
		model.addAttribute("resultsOnPage",
				groupService.getResultsOnPageCount());
		return "user/useractivation";
	}

	@RequestMapping(value = "next", method = RequestMethod.GET)
	public String nextPage(Model model) {
		model.addAttribute("userList", userService
				.findByActiveStatusPagination(prevCount, fieldsOnPageMax));
		model.addAttribute("resultsOnPage",
				groupService.getResultsOnPageCount());
		prevCount = prevCount + pagePrevAndNext;
		return "user/useractivation";
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/useractivate/{userId}")
	public String activate(@PathVariable("userId") Long id) {
		logger.info("activating user");
		logger.info("User id:" + id);
		User user = userService.findById(id);
		logger.info("Found user:" + user);
		user.setActive(1);
		userService.update(user);
		logger.info("user activated successfully");
		return "redirect:/useractivation";
	}

	@RequestMapping("/userdeactivate/{userId}")
	public String deactivate(@PathVariable("userId") Long id) {
		logger.info("activating user");
		logger.info("User id:" + id);
		User user = userService.findById(id);
		logger.info("Found user:" + user);
		user.setActive(0);
		userService.update(user);
		logger.info("user activated successfully");
		return "redirect:/useractivation";
	}

	/**
	 * @param all
	 *            users
	 * @return
	 */

	@RequestMapping(value="users", method=RequestMethod.GET)
	private String allUsers(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("userList",
				userService.findByDateOfCreating(fieldsOnPageMax));
		model.addAttribute("resultsOnPage",
				groupService.getResultsOnPageCount());
		return "users";
	}

	@RequestMapping(value = "result/{result}", method = RequestMethod.GET)
	public String allUsersOnPage(@PathVariable("result") int fieldsOnPage,
			Model model) {
		fieldsOnPageMax = fieldsOnPage;
		pagingCount = fieldsOnPage;
		pagePrevAndNext = fieldsOnPage;
		model.addAttribute("userList",
				userService.findByDateOfCreating(fieldsOnPageMax));
		model.addAttribute("resultsOnPage",
				groupService.getResultsOnPageCount());
		return "users";
	}
	
	@RequestMapping(value = "usersprev", method = RequestMethod.GET)
	public String userPrevPage(Model model) {
		model.addAttribute(
				"userList",
				userService.findByDateOfCreating(prevCount, fieldsOnPageMax));
		model.addAttribute("resultsOnPage",
				groupService.getResultsOnPageCount());
		prevCount = prevCount - pagePrevAndNext;
		return "users";
	}

	@RequestMapping(value = "userspage/{page}", method = RequestMethod.GET)
	public String usersPaging(@PathVariable("page") int page, Model model) {
		int paging = page - 1;
		pagingCount = fieldsOnPageMax * paging;
		model.addAttribute("userList", userService
				.findByDateOfCreating(pagingCount, fieldsOnPageMax));
		model.addAttribute("resultsOnPage",
				groupService.getResultsOnPageCount());
		return "users";
	}

	@RequestMapping(value = "usersnext", method = RequestMethod.GET)
	public String usersNextPage(Model model) {
		model.addAttribute("userList", userService
				.findByDateOfCreating(prevCount, fieldsOnPageMax));
		model.addAttribute("resultsOnPage",
				groupService.getResultsOnPageCount());
		prevCount = prevCount + pagePrevAndNext;
		return "users";
	}

	@RequestMapping("filterbylogin")
	private String allUsersByLogin(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("userList", userService.findByLoginASC(fieldsOnPageMax));
		model.addAttribute("resultsOnPage",
				groupService.getResultsOnPageCount());
		return "users";
	}

	@RequestMapping("filterbyfname")
	private String allUsersByFName(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("userList", userService.findByFName(fieldsOnPageMax));
		model.addAttribute("resultsOnPage",
				groupService.getResultsOnPageCount());
		return "users";
	}

	@RequestMapping("filterbyLname")
	private String allUsersByLName(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("userList", userService.findByLName(fieldsOnPageMax));
		model.addAttribute("resultsOnPage",
				groupService.getResultsOnPageCount());
		return "users";
	}

	@RequestMapping("filterbycreating")
	private String allUsersByCreating() {
		return "redirect:users";
	}

}
