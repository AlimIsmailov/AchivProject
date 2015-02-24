package com.ita.softserveinc.achiever.service;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.entity.Role;
import com.ita.softserveinc.achiever.entity.User;

@Component
public interface IRoleService extends IGenericService<Role>{
	
	public Role findByType(String name);
	
	public Set<User> getUsers (String roleName);
	
	public Set<User> getUsers (Role role);
	
	public Set<User> getUsers (Set<Role> roles);

	public Set<Role> findByType(Set<Role> roles);
}
