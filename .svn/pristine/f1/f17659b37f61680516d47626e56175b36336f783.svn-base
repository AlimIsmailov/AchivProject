package com.ita.softserveinc.achiever.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.ita.softserveinc.achiever.entity.Application;
import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.entity.User;
@Repository
public class ApplicationDaoImpl extends GenericDaoImpl<Application> implements
		IApplicationDao {

	public ApplicationDaoImpl() {

	}
	/**
	 * @throws NoElementException 
	 * 
	 */
	public Application findByUser(User user){
		Application foundApplication = null;
		try{
			foundApplication=entityManager.createNamedQuery("Application.findByUser", Application.class).setParameter("user", user).getSingleResult();
		}catch(NoResultException e){
			return null;
		}
		return foundApplication;
	}
	/**
	 * @throws NoElementException
	 */
	public List<Application> findByGroup(Group group) {
		List<Application> foundApplications = new ArrayList<Application>();
		try{
			foundApplications=entityManager.createNamedQuery("Application.findByGroup", Application.class).setParameter("group", group).getResultList();
		}catch(NoResultException e){
			return new ArrayList<Application>();
		}
		return foundApplications;
	}
}
