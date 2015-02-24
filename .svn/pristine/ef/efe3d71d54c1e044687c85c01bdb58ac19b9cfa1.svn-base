package com.ita.softserveinc.achiever.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.entity.Direction;
import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.entity.Location;
import com.ita.softserveinc.achiever.entity.User;
import com.ita.softserveinc.achiever.exception.ElementExistsException;
import com.ita.softserveinc.achiever.exception.InvalidDateException;

@Component
public interface ILocationService {

	void create(Location entity) throws ElementExistsException,
			InvalidDateException;

	Location update(Location entity) throws ElementExistsException,
			InvalidDateException;

	void delete(Location entity);

	Location findById(Long id);

	List<Location> findAll();

	Location findByName(String name);

}
