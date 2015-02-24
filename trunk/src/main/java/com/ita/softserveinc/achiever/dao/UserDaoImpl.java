package com.ita.softserveinc.achiever.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ita.softserveinc.achiever.entity.User;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements IUserDao {

	@Autowired
	private IRoleDao roleDao;

	public User findByLogin(String login) {
		User foundresult = null;
		try {
			foundresult = (User) entityManager
					.createNamedQuery("User.findByLogin", User.class)
					.setParameter("login", login).getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			return null;
		}
		return foundresult;
	}

	public List<User> findByActiveStatus() {
		List<User> foundresult = null;
		try {
			foundresult = (List<User>) entityManager.createNamedQuery(
					"User.findByActiveStatus", User.class).getResultList();
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			return null;
		}
		return foundresult;
	}

	public User findByEmail(String email) {
		User foundresult = null;
		try {
			foundresult = (User) entityManager
					.createNamedQuery("User.findByEmail", User.class)
					.setParameter("email", email).getSingleResult();
		} catch (PersistenceException e) {
			return null;
		}
		return foundresult;
	}

	@Override
	public void delete(User user) {
		user.setActive(0);
	}

	@Override
	public List<User> findAllByRole(String type) {
		List<User> foundUsers = null;
		try {
			foundUsers = entityManager
					.createNamedQuery("User.findAllByRoles", User.class)
					.setParameter("type", type).getResultList();
		} catch (PersistenceException e) {
			return null;
		}
		return foundUsers;

	}

	public List<User> findByGroup(String groupName) {
		List<User> foundUsers = null;
		try {
			foundUsers = entityManager
					.createNamedQuery("User.findByGroup", User.class)
					.setParameter("groupName", groupName).getResultList();
		} catch (PersistenceException e) {
			return null;
		}
		return foundUsers;
	}

	public List<User> findByGroup(String groupName, String roletype) {
		List<User> foundUsers = null;
		try {
			foundUsers = entityManager
					.createNamedQuery("User.findByGroupAndRole", User.class)
					.setParameter("groupName", groupName)
					.setParameter("type", roletype).getResultList();
		} catch (PersistenceException e) {
			return null;
		}
		return foundUsers;
	}

	@Override
	public List<User> findByRoleAndByGroup(String type, String groupName) {
		List<User> foundUsers = null;
		try {
			foundUsers = entityManager
					.createNamedQuery("User.findByRoleAndByGroup", User.class)
					.setParameter("groupName", groupName)
					.setParameter("type", type).getResultList();
		} catch (PersistenceException e) {
			return null;
		}
		return foundUsers;
	}

	@Override
	public List<User> findByDateOfCreating() {
		List<User> foundUsersD = null;
		foundUsersD = entityManager.createNamedQuery(
				"User.findByDateOfCreating", User.class).getResultList();
		return foundUsersD;
	}

	@Override
	public List<User> findByLoginASC() {
		List<User> foundUsersD = null;
		foundUsersD = entityManager.createNamedQuery("User.findByLoginASC",
				User.class).getResultList();
		return foundUsersD;
	}

}
