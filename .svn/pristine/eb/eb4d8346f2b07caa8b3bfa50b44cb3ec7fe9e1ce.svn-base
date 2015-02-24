package com.ita.softserveinc.achiever.dao;

import java.util.Collections;
import java.util.Set;

import javax.persistence.NoResultException;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.ita.softserveinc.achiever.entity.Role;
import com.ita.softserveinc.achiever.entity.User;

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role> implements IRoleDao {

	public Role findByType(String name) {

		Role foundresult = null;

		try {
			foundresult = (Role) entityManager
					.createNamedQuery("Role.findByName", Role.class)
					.setParameter("type", name).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

		return foundresult;
	}

	public Set<User> getUsers(Role role) {
		role.getUsers();
		return role.getUsers();
	}

	public Set<User> getUsers(String roleName) {
		Role role;

		role = findByType(roleName);
		if (role != null)
		role.getUsers();
		else
			return Collections.emptySet();
		return role.getUsers();
	}
}
