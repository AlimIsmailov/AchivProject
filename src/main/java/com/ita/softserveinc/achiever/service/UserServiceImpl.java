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
import org.springframework.web.multipart.MultipartFile;

import com.ita.softserveinc.achiever.api.EmailVerificationRequest;
import com.ita.softserveinc.achiever.api.PasswordRequest;
import com.ita.softserveinc.achiever.api.UpdateUserRequest;
import com.ita.softserveinc.achiever.dao.IGroupDao;
import com.ita.softserveinc.achiever.dao.IRoleDao;
import com.ita.softserveinc.achiever.dao.IUserDao;
import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.entity.Role;
import com.ita.softserveinc.achiever.entity.User;
import com.ita.softserveinc.achiever.exception.ImageUploadException;
import com.ita.softserveinc.achiever.exception.UserException;
import com.ita.softserveinc.achiever.exception.UserNotFoundException;
import com.ita.softserveinc.achiever.tool.UserListForm;
import com.ita.softserveinc.achiever.tool.UserSearchRequestForm;

@Service
public class UserServiceImpl implements IUserService {

	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);

	private String ROLE_BY_DEFAULT = "ROLE_USER";

	@Autowired
	private IUserDao userDao;

	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private IGroupDao groupDao;
	@Autowired
	private IRoleService roleService;

	private BCryptPasswordEncoder encoder;

	public UserServiceImpl() {
		encoder = new BCryptPasswordEncoder();
	}

	@Transactional
	public void create(User user) throws UserException {
		logger.info("User login check.");
		// assign the role ROLE_USER to user
		logger.info("Assign the role ROLE_USER to user");
		user.addRole(roleDao.findByType(ROLE_BY_DEFAULT));
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

	@Transactional
	public User updateName(String login, UpdateUserRequest updateUserRequest) {

		User user = userDao.findByLogin(login);
		String firstName = updateUserRequest.getFirstName();
		String lastName = updateUserRequest.getLastName();

		user.setFirstName(firstName);
		user.setLastName(lastName);

		return userDao.update(user);
	}

	@Transactional
	public User updateEmail(String login,
			EmailVerificationRequest emailVerificationRequest) {

		User user = userDao.findByLogin(login);
		String email = emailVerificationRequest.getEmail();
		user.setEmail(email);

		return userDao.update(user);
	}

	@Transactional
	public User updatePassword(String login, PasswordRequest passwordRequest) {

		User user = userDao.findByLogin(login);
		String password = encoder.encode(passwordRequest.getPassword());
		user.setPassword(password);
		return userDao.update(user);
	}

	@Transactional
	public User updateImage(String login, MultipartFile image) {

		User user = userDao.findByLogin(login);

		if (!image.isEmpty()) {
			validateImage(image);
			saveImage(user, image);
		}

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
		User user = userDao.findByLogin(login);
		if (user == null) {
			throw new UserNotFoundException();
		}
		return user;
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
	public List<User> findByActiveStatusResultOnPage(int pageMax) {
		return userDao.findByActiveStatusResultOnPage(pageMax);
	}

	@Override
	public List<User> findByActiveStatus() {
		return userDao.findByActiveStatus();
	}

	@Override
	public List<User> findByActiveStatusPagination(int pagination, int maxResult) {
		return userDao.findByActiveStatusPagination(pagination, maxResult);
	}

	@Override
	public List<User> findByFName(int maxResult) {
		return userDao.findByFName(maxResult);
	}

	@Override
	public List<User> findByLName(int maxResult) {
		return userDao.findByLName(maxResult);
	}

	@Override
	public List<User> findByLoginASC(int maxResult) {
		return userDao.findByLoginASC(maxResult);
	}
	
	@Override
	public List<User> findByDateOfCreating(int maxResult) {
		return userDao.findByDateOfCreating(maxResult);
	}
	
	@Override
	public List<User> findByDateOfCreating(int pagination, int maxResult) {
		return userDao.findByDateOfCreating(pagination, maxResult);
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

	@Override
	public List<User> findStudentByGroupName(String groupName) {
		return userDao.findByGroup(groupName);
	}

	@Override
	public List<User> findByRoleAndByGroup(String type, String groupName) {
		return userDao.findByRoleAndByGroup(type, groupName);
	}

	public List<User> findByUserRequest(UserSearchRequestForm searchRequest) {
		return userDao.findByUserRequest(searchRequest);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateUsersByForm(UserListForm userListForm) {
		List<User> userList = userListForm.getModifiedPageContent();

		if (userList != null) {
			for (User user : userList) {
				Set<Role> roleSet = new HashSet<Role>();
				User userFromDB = findById(user.getId());

				if (userFromDB == null)
					continue;
				if (user.getRoles() == null)
					continue;

				userFromDB.setActive(user.getActive());
				update(userFromDB);

				for (Role role : user.getRoles()) {
					Role roleFromDB;
					roleFromDB = roleService.findByType(role.getType());
					if (roleFromDB == null)
						continue;
					roleSet.add(roleFromDB);
				}
				if (roleSet.size() != 0) {
					userFromDB.setRoles(roleSet);
					update(userFromDB);
				}
			}
		}
	}
<<<<<<< .mine

	private void validateImage(MultipartFile image) {
		if (!image.getContentType().equals("image/jpeg")) {
			throw new ImageUploadException("Only JPG images accepted");
		}
	}

	private void saveImage(User user, MultipartFile image) {
		try {

			byte[] uploadImage = image.getBytes();
			user.setImage(uploadImage);

		} catch (Exception e) {
			throw new ImageUploadException("Unable to save image", e);
		}

	}

=======

>>>>>>> .r1221
}
