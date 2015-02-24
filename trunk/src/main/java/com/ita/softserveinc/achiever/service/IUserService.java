package com.ita.softserveinc.achiever.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.entity.Role;
import com.ita.softserveinc.achiever.entity.User;
import com.ita.softserveinc.achiever.exception.UserException;

@Component
public interface IUserService {

	void create(User entity) throws UserException;

	User update(User entity);

	void delete(User entity);

	User findById(Long id);

	List<User> findAll();

	User findByLogin(String login);
	
	User findByEmail(String email);

	public List<User> findAllByRole(String type);

	List<User> findManagers();

	Set<User> stringToManagers(String[] logins);

	List<User> findStudentsByGroup(Group group);
	
	List<User> findByActiveStatus();

	List<User> findByGroup(Group group);

	List<User> findByGroup(Group group, Role role);

	List<User> findByDateOfCreating();
	
	List<User> findByLoginASC();
	
	boolean isOnlyUser(String login);


}
