package com.ita.softserveinc.achiever.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ita.softserveinc.achiever.dao.ILocationDao;
import com.ita.softserveinc.achiever.entity.Location;
import com.ita.softserveinc.achiever.exception.ElementExistsException;

@Service
public class LocationServiceImpl implements ILocationService {

	@Autowired
	private ILocationDao locationDao;

	/*
	 * Create new location in datebase
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(Location entity) throws ElementExistsException {
		if (findByName(entity.getName()) != null) {
			throw new ElementExistsException();
		}
		locationDao.create(entity);
	}

	/*
	 * Update exist location in datebase
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Location update(Location entity) throws ElementExistsException {
		if ((findByName(entity.getName()) != null)) {
			throw new ElementExistsException();
		}
		return locationDao.update(entity);
	}

	/*
	 * Delete location from datebase
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Location entity) {
		locationDao.delete(entity);
	}

	/*
	 * Find particular location by ID
	 */
	public Location findById(Long id) {
		return locationDao.findById(Location.class, id);
	}

	/*
	 * Find all locations from datebase
	 */
	public List<Location> findAll() {
		return locationDao.findAll(Location.class);
	}

	/*
	 * Find particular location by name
	 */
	@Override
	public Location findByName(String name) {
		return locationDao.findByName(name);
	}

	/*
	 * Method receive from controller name of subject. Method return list of
	 * locations depending by subject (group, manager or location) In JSP modal
	 * for creation/update Event for particular location, admin can't
	 * create/update Event for another location
	 */

	@Override
	public List<Location> findLocationForJsp(String varFromJsp) {

		List<Location> locations = new ArrayList<Location>();

		if (findByName(varFromJsp) != null) {
			locations.add(findByName(varFromJsp));
		}
		locations = findAll();
		return locations;
	}

}
