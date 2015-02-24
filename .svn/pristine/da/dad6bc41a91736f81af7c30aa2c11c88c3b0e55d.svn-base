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

	List<User> findByGroup(String groupName, String roletype);

	List<User> findByDateOfCreating(int maxResult);

	List<User> findByActiveStatus();

	List<User> findByActiveStatusResultOnPage(int maxPage);

	List<User> findByActiveStatusPagination(int pagination, int maxResult);
	
	List<User> findByLoginASC(int maxResult);

	List<User> findByFName(int maxResult);

	List<User> findByLName(int maxResult);
	
	List<User> findByUserRequest(UserSearchRequestForm searchRequest);

	List<User> findByDateOfCreating(int pagination, int maxResult);

}
