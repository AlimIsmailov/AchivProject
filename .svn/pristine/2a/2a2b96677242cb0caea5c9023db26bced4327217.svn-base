package com.ita.softserveinc.achiever.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
import com.ita.softserveinc.achiever.paging.GroupPaging;
import com.ita.softserveinc.achiever.tool.DateParser;
import com.ita.softserveinc.achiever.tool.GroupFormBean;
import com.ita.softserveinc.achiever.tool.GroupRatingInfoDto;

@Service
public class GroupServiceImpl implements IGroupService {

	private static final Logger LOG = LoggerFactory
			.getLogger(GroupServiceImpl.class);

	private static final short APPLICATION_NOT_REVIEWED = 0;

	@Autowired
	private IGroupDao groupDao;

	@Autowired
	private IUserService userService;

	@Autowired
	private IDirectionService directionService;

	@Autowired
	private IApplicationService applicationService;
	
	
	private DateParser dateParser = new DateParser();

	private static Map<String, List<Group>> chosenGroups = new HashMap<String, List<Group>>();

	@Transactional(propagation = Propagation.REQUIRED)
	public void create(Group entity) throws ElementExistsException,
			InvalidDateException {
		Date start = entity.getStart();
		Date end = entity.getEnd();
		if (groupDao.findByName(entity.getGroupName()) != null) {
			throw new ElementExistsException();
		}
		if (!(dateParser.isValidDates(start, end))) {
			throw new InvalidDateException();
		}
		groupDao.create(entity);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Group update(Group entity) throws ElementExistsException,
			InvalidDateException {
		Date start = entity.getStart();
		Date end = entity.getEnd();
		if (!(dateParser.isValidDates(start, end))) {
			throw new InvalidDateException();
		}

		if (groupDao.findByName(entity.getGroupName()) != null) {
			if (groupDao.findByName(entity.getGroupName()).getId()
					.equals(entity.getId())) {
				return groupDao.update(entity);
			}
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


	@Override
	public List<Group> getChosenGroups2(boolean isFuture, boolean isCurrent,
			boolean isFinished, String login) {
		User user = userService.findByLogin(login);
		boolean isAdmin = userService.findAllByRole("ROLE_ADMIN")
				.contains(user);
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
	public List<Group> getFilteredGroups(String directionName,
			String startFrom, String startTo, String endFrom, String endTo,
			String login) {
		User user = userService.findByLogin(login);
		boolean isAdmin = userService.findAllByRole("ROLE_ADMIN")
				.contains(user);
		Date startFromDate = dateParser.parseDate(startFrom);
		Date startToDate = dateParser.parseDate(startTo);
		Date endFromDate = dateParser.parseDate(endFrom);
		Date endToDate = dateParser.parseDate(endTo);
		List<Group> groupList = new ArrayList<Group>();

		if (isAdmin) {
			if ((startFrom == null) || (startFrom == "")) {
				startFromDate = dateParser.parseDate("1900-01-01");
			}
			if ((startTo == null) || (startTo == "")) {
				startToDate = dateParser.parseDate("2100-01-01");
			}
			if ((endFrom == null) || (endFrom == "")) {
				endFromDate = dateParser.parseDate("1900-01-01");
			}
			if ((endTo == null) || (endTo == "")) {
				endToDate = dateParser.parseDate("2100-01-01");
			}
			if (isAdmin) {
				groupList = groupDao.getFilteredGroups(directionName,
						startFromDate, startToDate, endFromDate, endToDate);
			} else {
				groupList = groupDao.getFilteredGroups(directionName,
						startFromDate, startToDate, endFromDate, endToDate,
						login);
			}
		}
		return groupList;
	}

	@Override
	public Group getGroup(GroupFormBean groupFormBean)
			throws ElementExistsException, InvalidDateException {
		Date start = dateParser.parseDate(groupFormBean.getStart());
		Date end = dateParser.parseDate(groupFormBean.getEnd());
		Set<User> managers = userService.stringToManagers(groupFormBean
				.getUsers());
		Group group = groupFormBean.getGroup();
		group.setGroupName(groupFormBean.getName());
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
		Date start = dateParser.parseDate(groupFormBean.getStart());
		Date end = dateParser.parseDate(groupFormBean.getEnd());
		Set<User> managers = userService.stringToManagers(groupFormBean
				.getUsers());
		Direction direction = directionService.findByName(groupFormBean
				.getGroup().getDirection().getName());
		eGroup.setGroupName(groupFormBean.getName());
		eGroup.setStart(start);
		eGroup.setEnd(end);
		eGroup.setDirection(direction);
		eGroup.addUsers(managers);
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
		application.setStatus(APPLICATION_NOT_REVIEWED);
		if (applicationService.findByUser(user) != null) {
			throw new ElementExistsException();
		}
		applicationService.create(application);
	}

	@Override
	public List<Integer> getResultsOnPageCount() {
		try {
			JAXBContext jaxbContext = JAXBContext
					.newInstance(GroupPaging.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			ClassPathResource cpr = new ClassPathResource(
					"/jaxb-xml/group-paging-configuration.xml");
			InputStream inputs = cpr.getInputStream();
			GroupPaging result = (GroupPaging) unmarshaller.unmarshal(inputs);
			return result.getCountOfResults();
		} catch (JAXBException e) {
			 LOG.info("JAXB Exception");
		} catch (IOException e) {
			 LOG.info("File not found");
		}
		return null;
	}

	@Override
	public List<Group> findByDirectionName(String directionName) {
		Direction direction = directionService.findByName(directionName);

		return groupDao.findByDirection(direction);
	}

	@Override
	public List<GroupRatingInfoDto> getGroupInfo(String groupName) {
		final String ROLE_MANAGER = "ROLE_MANAGER";
		Group group = new Group();
		List<User> managers = new ArrayList<User>();
		List<GroupRatingInfoDto> groupsRatingInfoDto = new ArrayList<GroupRatingInfoDto>();
		GroupRatingInfoDto groupInfo = new GroupRatingInfoDto();

		managers.addAll(userService.findByRoleAndByGroup(ROLE_MANAGER,
				groupName));
		group = groupDao.findByName(groupName);

		groupInfo.setStartDate(new DateTime(group.getStart()));
		groupInfo.setEndDate(new DateTime(group.getEnd()));
		groupInfo.setGroupManager(managers);
		LOG.info(managers.toString());
		groupsRatingInfoDto.add(groupInfo);
		return groupsRatingInfoDto;
	}

}
