package com.ita.softserveinc.achiever.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.entity.Direction;
import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.entity.User;

@Component
public interface IGroupDao extends IGenericDao<Group> {

	List<Group> findByDirection(Direction direction);

	Group findByName(String name);

	List<Group> findByUser(User manager);
	
	List<Group> findByStartDate(Date date);
	
	List<Group> findByEndDate(Date date);

	List<Group> findStartAfterDate(Date date);

	List<Group> findEndBeforeDate(Date date);

	List<Group> findStartAfterDateByUser(Date date, String login);

	List<Group> findEndBeforeDateByUser(Date date, String login);
	
	List<Group> findCurrentGroups(Date today);
	
	List<Group> findCurrentGroupsByUser(Date today, String login);

	List<Group> findByDirection(String directionName);

	List<Group> findStartBeforeDate(Date date);

	List<Group> findEndAfterDate(Date date);

	List<Group> findStartBeforeDateByUser(Date date, String login);

	List<Group> findEndAfterDateByUser(Date date, String login);

	List<Group> findByDirectionNameAndUser(String directionName, String login);

	List<Group> getFilteredGroups(String directionName, Date startFrom,
			Date startTo, Date endFrom, Date endTo);

	List<Group> getFilteredGroups(String directionName, Date startFrom,
			Date startTo, Date endFrom, Date endTo, String login);


}