package com.ita.softserveinc.achiever.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.entity.Application;
import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.entity.User;

@Component
public interface IApplicationDao extends IGenericDao<Application> {
	/**
	 * 
	 * @param user
	 * @return
	 * @throws NoElementException
	 */
	Application findByUser(User user);
	
	/**
	 * 
	 * @param group
	 * @return
	 * @throws NoElementException
	 */
	List<Application> findByGroup(Group group);

}
