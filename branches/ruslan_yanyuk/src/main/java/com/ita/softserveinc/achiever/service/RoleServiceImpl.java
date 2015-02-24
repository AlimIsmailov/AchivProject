package com.ita.softserveinc.achiever.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ita.softserveinc.achiever.dao.IRoleDao;
import com.ita.softserveinc.achiever.entity.Role;
import com.ita.softserveinc.achiever.entity.User;
import com.ita.softserveinc.achiever.exception.ElementExistsException;

@Service
public class RoleServiceImpl implements IRoleService{

	@Autowired
	private IRoleDao roleDao;
	
	@Transactional (propagation = Propagation.REQUIRED)
	public void create(Role role) {
		try {
			roleDao.create(role);
		} catch (ElementExistsException e) {
			e.printStackTrace();
		}
	}

	@Transactional (propagation = Propagation.REQUIRED)
	public Role update(Role role) {
		return roleDao.update(role);
	}

	
	@Transactional (propagation = Propagation.REQUIRED)
	public void delete(Role role) {
		roleDao.delete(role);
	}
	

	public Role findById(Long id) {
		return roleDao.findById(Role.class, id);
	}

	public List<Role> findAll() {
		return roleDao.findAll(Role.class);
	}

	public Role findByType(String name) {
		return roleDao.findByType(name);
	}
	
	public Set<User> getUsers (String roleName) {
		return roleDao.getUsers(roleName);
	}
	
	public Set<User> getUsers (Role role) {
		return roleDao.getUsers(role);
	}

	public Set<User> getUsers (Set<Role> roles) {
		Set<User> resultSet = new HashSet<User>();
		for (Role role : roles) {
			resultSet.addAll(getUsers(role));
		}
		return resultSet;
	}

	@Override
	public Set<Role> findByType(Set<Role> roles) {
		Set<Role> resultSet = new HashSet<Role>();
		for (Role role : roles) {
			resultSet.add(roleDao.findByType(role.getType()));
		}
		return resultSet;
	}

}
