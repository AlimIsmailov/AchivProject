package com.ita.softserveinc.achiever.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.entity.Application;
import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.entity.User;
import com.ita.softserveinc.achiever.exception.ElementExistsException;

@Component
public interface IApplicationService{
	
	void create(Application entity) throws ElementExistsException;
	
	void delete(Application entity);
	
	Application findById(Long id);
	
	List<Application> findAll();
	
	

	/**
	 * 
	 * @param user
	 * @return
	 */
	Application findByUser(User user);

	/**
	 * 
	 * @param group
	 * @return
	 */
	List<Application> findByGroup(Group group);

	/**
	 * 
	 * @param name
	 * @return
	 */
	List<Application> findByManagerLogin(String name);
	
	void confirmApplication(Long id);
	
	void delete(Long id);

}
