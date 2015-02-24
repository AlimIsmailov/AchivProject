package com.ita.softserveinc.achiever.tool;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.dao.IGroupDao;
import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.entity.Role;
import com.ita.softserveinc.achiever.entity.User;
import com.ita.softserveinc.achiever.service.IGroupService;
import com.ita.softserveinc.achiever.service.IRoleService;
import com.ita.softserveinc.achiever.service.IUserService;

@Component
public class GroupListener {

	@Autowired
	private IGroupService groupService;

	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IGroupDao groupDao;

	private static final Logger logger = LoggerFactory
			.getLogger(GroupListener.class);
	
	@Scheduled(fixedRate=86400000)
	public void checkGroupsListener(){
		logger.info("Listener started!");
		Calendar currentDate = Calendar.getInstance();
		Date today = new Date(System.currentTimeMillis());
		currentDate.setTime(today);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		currentDate.set(Calendar.MILLISECOND, 0);
		Date todayForm = new Date(currentDate.getTimeInMillis());
		Role student = roleService.findByType("ROLE_STUDENT");
		Role manager = roleService.findByType("ROLE_MANAGER");
		List<Group> startedGroups = groupDao.findByStartDate(todayForm);
		for (Group startedGroup : startedGroups) {
				for (User user : startedGroup.getUsers()) {
					user = userService.findByLogin(user.getLogin());
					if (!(user.getRoles().contains(manager))){
						user.addRole(student);
						userService.update(user);
					}
				}
			}
		List<Group> endedGroups = groupDao.findByEndDate(todayForm);
		for (Group endedGroup: endedGroups){
				for (User user : endedGroup.getUsers()) {
					user = userService.findByLogin(user.getLogin());
					if (!(user.getRoles().contains(manager))){
						user.removeRole(student);
						userService.update(user);
					}
				}
			}
		logger.info("Listener ended!");
	}

}
