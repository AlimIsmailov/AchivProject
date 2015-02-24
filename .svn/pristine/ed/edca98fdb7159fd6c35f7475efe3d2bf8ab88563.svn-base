package com.ita.softserveinc.achiever.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ita.softserveinc.achiever.dao.IGroupDao;
import com.ita.softserveinc.achiever.entity.Application;
import com.ita.softserveinc.achiever.entity.Direction;
import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.entity.User;
import com.ita.softserveinc.achiever.exception.ElementExistsException;
import com.ita.softserveinc.achiever.exception.InvalidDateException;
import com.ita.softserveinc.achiever.tool.DateValidator;
import com.ita.softserveinc.achiever.tool.GroupFormBean;

@Service
public class GroupServiceImpl implements IGroupService {

	@Autowired
	private IGroupDao groupDao;

	@Autowired
	private IUserService userService;

	@Autowired
	private DateValidator dateValidator;

	@Autowired
	private IDirectionService directionService;

	@Autowired
	private IApplicationService applicationService;

	private static Map<String, List<Group>> chosenGroups = new HashMap<String, List<Group>>();

	@Transactional(propagation = Propagation.REQUIRED)
	public void create(Group entity) throws ElementExistsException,
			InvalidDateException {
		Date start = entity.getStart();
		Date end = entity.getEnd();
		if (groupDao.findByName(entity.getGroupName()) != null) {
			throw new ElementExistsException();
		}
		if (!(isValidDates(start, end))) {
			throw new InvalidDateException();
		}
		groupDao.create(entity);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Group update(Group entity) throws ElementExistsException,
			InvalidDateException {
		Date start = entity.getStart();
		Date end = entity.getEnd();
		if (!(isValidDates(start, end))) {
			throw new InvalidDateException();
		}

		if ((groupDao.findByName(entity.getGroupName()) == null)
				&& (groupDao.findByName(entity.getGroupName()).getId()
						.equals(entity.getId()))) {
			return groupDao.update(entity);
		}
		if (groupDao.findByName(entity.getGroupName()) != null) {
			throw new ElementExistsException();
		}
		return groupDao.update(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Group entity) {
		groupDao.delete(entity);
	}

	public Group findById(Long id) {
		return groupDao.findById(Group.class, id);
	}

	public List<Group> findAll() {
		return groupDao.findAll(Group.class);
	}

	@Override
	public Group findByName(String name) {
		return groupDao.findByName(name);
	}

	@Override
	public List<Group> findByDirection(Direction direction) {
		return groupDao.findByDirection(direction);
	}

	public Date parseDate(String date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDate = null;
		try {
			parsedDate = dateFormat.parse(date);
		} catch (ParseException e) {
			return null;
		}
		long time = parsedDate.getTime();
		return new Date(time);
	}

	public boolean isValidDates(Date startTime, Date endTime) {
		if ((startTime == null) || (endTime == null)) {
			return false;
		}
		if ((endTime.before(startTime)) || (endTime.equals(startTime))) {
			return false;
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		String startTimeStr = dateFormat.format(startTime);
		String endTimeStr = dateFormat.format(endTime);
		if ( (dateValidator.validate(startTimeStr)==true) && (dateValidator.validate(endTimeStr)==true)){
			return true;
		}
		
		
		return true;
	}

	@Override
	public List<Group> findByUser(User manager) {
		return groupDao.findByUser(manager);
	}

	@Override
	public List<Group> findByUser(String login) {
		User user = userService.findByLogin(login);
		return findByUser(user);
	}

	@Override
	public List<Group> findFutureGroups() {
		return groupDao
				.findStartAfterDate(new Date(System.currentTimeMillis()));
	}

	@Override
	public List<Group> findFutureGroups(String login) {
		return groupDao.findStartAfterDateByUser(
				new Date(System.currentTimeMillis()), login);

	}

	@Override
	public List<Group> findFinishedGroups(String login) {
		return groupDao.findEndBeforeDateByUser(
				new Date(System.currentTimeMillis()), login);
	}

	@Override
	public List<Group> findFinishedGroups() {
		return groupDao.findEndBeforeDate(new Date(System.currentTimeMillis()));
	}

	@Override
	public List<Group> findCurrentGroups(String login) {
		return groupDao.findCurrentGroupsByUser(
				new Date(System.currentTimeMillis()), login);
	}

	@Override
	public List<Group> findCurrentGroups() {
		return groupDao.findCurrentGroups(new Date(System.currentTimeMillis()));
	}

	public List<Group> getChosenGroups(GroupFormBean groupFormBean, String login) {
		User user = userService.findByLogin(login);
		boolean isAdmin = userService.findAllByRole("ROLE_ADMIN")
				.contains(user);
		boolean isFuture = groupFormBean.getIsFuture();
		boolean isCurrent = groupFormBean.getIsCurrent();
		boolean isFinished = groupFormBean.getIsFinished();
		List<Group> groupList = new ArrayList<Group>();

		if (isFuture) {
			if (isAdmin == true) {
				groupList.addAll(findFutureGroups());
			} else {
				groupList.addAll(findFutureGroups(login));
			}
		}
		if (isCurrent) {
			if (isAdmin == true) {
				groupList.addAll(findCurrentGroups());
			} else {
				groupList.addAll(findCurrentGroups(login));
			}
		}
		if (isFinished) {
			if (isAdmin == true) {
				groupList.addAll(findFinishedGroups());
			} else {
				groupList.addAll(findFinishedGroups(login));
			}
		}
		chosenGroups.put(login, groupList);

		return groupList;
	}

	public List<Group> getChosenGroups(String login) {
		return chosenGroups.get(login);
	}

	@Override
	public Group getGroup(GroupFormBean groupFormBean)
			throws ElementExistsException, InvalidDateException {
		Date start = parseDate(groupFormBean.getStart());
		Date end = parseDate(groupFormBean.getEnd());
		Set<User> managers = userService.stringToManagers(groupFormBean
				.getUsers());
		Group group = groupFormBean.getGroup();
		group.setStart(start);
		group.setEnd(end);
		group.setUsers(managers);
		Direction direction = group.getDirection();
		group.setDirection(directionService.findByName(direction.getName()));
		return group;

	}

	@Override
	public Group getGroupToEdit(GroupFormBean groupFormBean)
			throws ElementExistsException, InvalidDateException {
		Group eGroup = findById(groupFormBean.getGroup().getId());
		Date start = parseDate(groupFormBean.getStart());
		Date end = parseDate(groupFormBean.getEnd());
		Set<User> managers = userService.stringToManagers(groupFormBean
				.getUsers());
		Direction direction = directionService.findByName(groupFormBean
				.getGroup().getDirection().getName());
		eGroup.setGroupName(groupFormBean.getGroup().getGroupName());
		eGroup.setStart(start);
		eGroup.setEnd(end);
		eGroup.setDirection(direction);
		eGroup.addUsers(managers);
		/* update(eGroup); */
		return eGroup;

	}

	@Override
	public void applyToGroup(Long groupId, String login)
			throws ElementExistsException {
		Group group = findById(groupId);
		User user = userService.findByLogin(login);
		Application application = new Application();
		application.setUser(user);
		application.setGroup(group);
		if (applicationService.findByUser(user) != null) {
			throw new ElementExistsException();
		}
		applicationService.create(application);
	}

}
