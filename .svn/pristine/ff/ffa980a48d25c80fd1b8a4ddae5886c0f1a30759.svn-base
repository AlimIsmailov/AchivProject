package com.ita.softserveinc.achiever.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ita.softserveinc.achiever.dao.IRoleDao;
import com.ita.softserveinc.achiever.dao.IUserDao;
import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.entity.Role;
import com.ita.softserveinc.achiever.entity.User;
import com.ita.softserveinc.achiever.exception.UserException;

@Service
public class UserServiceImpl implements IUserService {

	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);
	@Autowired
	private IUserDao userDao;

	@Autowired
	private IRoleDao roleDao;

	@Autowired
	private IRoleService roleService;

	private BCryptPasswordEncoder encoder;

	public UserServiceImpl() {
		encoder = new BCryptPasswordEncoder();
	}

	@Transactional
	public void create(User user) throws UserException {
		logger.info("User login check.");
		logger.info("User password" + user.getPassword());
		// assign the role ROLE_USER to user
		logger.info("Assign the role ROLE_USER to user");
		user.addRole(roleDao.findByType("ROLE_USER"));
		logger.info("Password encryption");
		// password encryption
		user.setPassword(encoder.encode(user.getPassword()));
		userDao.create(user);
		logger.info("User registration complete");
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public User update(User user) {
		return userDao.update(user);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(User user) {
		userDao.delete(user);
	}

	public User findById(Long id) {
		User user = userDao.findById(User.class, id);
		return user;
	}

	public List<User> findAll() {
		return userDao.findAll(User.class);
	}

	public User findByLogin(String login) {
		return userDao.findByLogin(login);
	}

	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	public List<User> findAllByRole(String type) {
		return userDao.findAllByRole(type);
	}

	@Override
	public List<User> findManagers() {
		return userDao.findAllByRole("ROLE_MANAGER");
	}

	@Override
	public List<User> findByGroup(Group group) {
		return userDao.findByGroup(group.getGroupName());
	}

	@Override
	public List<User> findByGroup(Group group, Role role) {
		List<User> users = userDao.findByGroup(group.getGroupName(),
				role.getType());
		return users != null ? users : new ArrayList<User>();
	}

	@Override
	public Set<User> stringToManagers(String[] logins) {
		Set<User> managers = new HashSet<User>();
		for (String login : logins) {
			managers.add(findByLogin(login));
		}
		return managers;
	}

	@Override
	public List<User> findStudentsByGroup(Group group) {
		return userDao.findByRoleAndByGroup("ROLE_STUDENT",
				group.getGroupName());
	}

	@Override
	public List<User> findByActiveStatus() {
		return userDao.findByActiveStatus();
	}

	@Override
	public List<User> findByDateOfCreating() {
		return userDao.findByDateOfCreating();
	}

	@Override
	public List<User> findByLoginASC() {
		return userDao.findByLoginASC();
	}

	@Override
	public boolean isOnlyUser(String login) {
		User user = findByLogin(login);
		Role userRole = roleService.findByType("ROLE_USER");
		Set<Role> roles = user.getRoles();
		if (roles.size() == 1) {
			if (user.getRoles().contains(userRole)) {
				return true;
			}
		}
		return false;
	}
}
