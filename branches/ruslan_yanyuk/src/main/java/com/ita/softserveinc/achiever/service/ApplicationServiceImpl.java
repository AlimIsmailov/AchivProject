package com.ita.softserveinc.achiever.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ita.softserveinc.achiever.controller.ApplicationController;
import com.ita.softserveinc.achiever.dao.IApplicationDao;
import com.ita.softserveinc.achiever.entity.Application;
import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.entity.User;
import com.ita.softserveinc.achiever.exception.ElementExistsException;
import com.ita.softserveinc.achiever.exception.InvalidDateException;

@Service
public class ApplicationServiceImpl implements IApplicationService {

	private static final Logger LOG = LoggerFactory
			.getLogger(ApplicationController.class);

	@Autowired
	private IApplicationDao applicationDao;

	@Autowired
	private IUserService userService;

	@Autowired
	private IGroupService groupService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(Application entity) throws ElementExistsException {
		applicationDao.create(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Application entity) {
		applicationDao.delete(entity);

	}

	@Override
	public Application findById(Long id) {
		return applicationDao.findById(Application.class, id);
	}

	@Override
	public List<Application> findAll() {
		return applicationDao.findAll(Application.class);
	}

	@Override
	public Application findByUser(User user) {
		Application foundApplication = null;
		foundApplication = applicationDao.findByUser(user);
		return foundApplication;
	}

	@Override
	public List<Application> findByGroup(Group group) {
		List<Application> foundApplication = null;
		foundApplication = applicationDao.findByGroup(group);
		return foundApplication;
	}

	@Override
	public List<Application> findByManagerLogin(String name) {
		List<Application> applicationList = new ArrayList<Application>();
		User manager = userService.findByLogin(name);
		List<Group> managersGroups = groupService.findByUser(manager);
		for (Group managerGroup : managersGroups) {
			applicationList.addAll(findByGroup(managerGroup));
		}
		return applicationList;
	}

	public void confirmApplication(Long id) {
		Application application = findById(id);
		Group group = application.getGroup();
		User user = application.getUser();
		user = userService.findByLogin(user.getLogin());
		group.addUser(user);
		try {
			groupService.update(group);
		} catch (ElementExistsException e) {
			LOG.info("Element Exists Exception");
		} catch (InvalidDateException e) {
			LOG.info("Invalid Date Exception");
		}
		delete(application);
	}

	@Override
	public void delete(Long id) {
		Application application = findById(id);
		delete(application);

	}

}
