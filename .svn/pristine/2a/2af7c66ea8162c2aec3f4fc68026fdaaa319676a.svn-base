package com.ita.softserveinc.achiever.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ita.softserveinc.achiever.entity.User;
import com.ita.softserveinc.achiever.service.IUserService;

@Controller
public class ManagerController {
	/**
	 * @author Alim Ismailov
	 */

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);
	@Autowired
	private IUserService userService;

	@RequestMapping("useractivation")
	public String inactiveUsers(Map<String, Object> map) {
		map.put("user", new User());
		map.put("userList", userService.findByActiveStatus());
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

	/*@RequestMapping("users")
	public String paging(Map<String, Object> map) {
		map.put("user", User.class);
		map.put("userList", new PageRequest(0, 10, Direction.ASC, "login"));
		return "users";
	}*/
	
	@RequestMapping("users")
	private String allUsers(Map<String, Object> map) {
		map.put("user", new User());
		map.put("userList", userService.findByDateOfCreating());
		return "users";
	}

	@RequestMapping("filterbylogin")
	private String allUsersByLogin(Map<String, Object> map) {
		map.put("user", new User());
		map.put("userList", userService.findByLoginASC());
		return "filterbylogin";
	}

	@RequestMapping("usersheap")
	private String usersheap(Map<String, Object> map) {
		map.put("user", new User());
		map.put("userList", userService.findByDateOfCreating());
		return "users";
	}

}
