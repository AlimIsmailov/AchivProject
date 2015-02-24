package com.ita.softserveinc.achiever.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.entity.User;
import com.ita.softserveinc.achiever.tool.UserSearchRequestForm;

@Component
public interface IUserDao extends IGenericDao<User> {

	User findByLogin(String login);

	User findByEmail(String email);

	public List<User> findAllByRole(String type);

	List<User> findByGroup(String groupName);

	List<User> findByRoleAndByGroup(String type, String groupName);

	List<User> findByActiveStatus();

	List<User> findByGroup(String groupName, String roletype);

	List<User> findByDateOfCreating();
	
	List<User> findByLoginASC();
	
	public List<User> findByUserRequest(UserSearchRequestForm searchRequest);

}
