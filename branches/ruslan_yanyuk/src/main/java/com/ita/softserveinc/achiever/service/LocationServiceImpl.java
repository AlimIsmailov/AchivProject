package com.ita.softserveinc.achiever.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ita.softserveinc.achiever.dao.ILocationDao;
import com.ita.softserveinc.achiever.entity.Location;
import com.ita.softserveinc.achiever.exception.ElementExistsException;
import com.ita.softserveinc.achiever.exception.InvalidDateException;

@Service
public class LocationServiceImpl implements ILocationService {

	@Autowired
	private ILocationDao locationDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public void create(Location entity) throws ElementExistsException,
			InvalidDateException {
		if (locationDao.findByName(entity.getName()) != null) {
			throw new ElementExistsException();
		}
		locationDao.create(entity);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Location update(Location entity) throws ElementExistsException,
			InvalidDateException {
		if (locationDao.findByName(entity.getName()) != null) {
			throw new ElementExistsException();
		}
		return locationDao.update(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Location entity) {
		locationDao.delete(entity);
	}

	public Location findById(Long id) {
		return locationDao.findById(Location.class, id);
	}

	public List<Location> findAll() {
		return locationDao.findAll(Location.class);
	}

	@Override
	public Location findByName(String name) {
		return locationDao.findByName(name);
	}

}
