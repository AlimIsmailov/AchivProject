package com.ita.softserveinc.achiever.dao;

import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.ita.softserveinc.achiever.entity.Role;
import com.ita.softserveinc.achiever.entity.User;

@Component
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public interface IRoleDao extends IGenericDao<Role>{

	Role findByType(String name);
	
	Set<User> getUsers (String roleName);
	
	Set<User> getUsers (Role role);
}
