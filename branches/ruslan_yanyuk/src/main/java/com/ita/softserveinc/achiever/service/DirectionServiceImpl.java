package com.ita.softserveinc.achiever.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ita.softserveinc.achiever.dao.IDirectionDao;
import com.ita.softserveinc.achiever.entity.Direction;
import com.ita.softserveinc.achiever.exception.ElementExistsException;

@Service
public class DirectionServiceImpl implements IDirectionService {

	@Autowired
	private IDirectionDao directionDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public void create(Direction entity) throws ElementExistsException{
			directionDao.create(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Direction update(Direction entity) throws ElementExistsException {
		if ((findByName(entity.getName()) != null)) {
			throw new ElementExistsException();
		}
		return directionDao.update(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Direction entity) {
		directionDao.delete(entity);
	}

	public Direction findById(Long id) {
		return directionDao.findById(Direction.class, id);
	}

	public List<Direction> findAll() {
		return directionDao.findAll(Direction.class);
	}

	@Override
	public Direction findByName(String name) {
			return directionDao.findByName(name);
	}

}
