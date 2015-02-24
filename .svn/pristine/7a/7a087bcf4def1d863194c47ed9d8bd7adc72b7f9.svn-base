package com.ita.softserveinc.achiever.dao;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.ita.softserveinc.achiever.entity.Direction;

/**
 * 
 * @author Andriana
 *
 */
@Repository
public class DirectionDaoImpl extends GenericDaoImpl<Direction> implements
		IDirectionDao {

	public DirectionDaoImpl() {

	}

	public Direction findByName(String name) {
		Direction foundresult = null;
		try{
		foundresult = entityManager
				.createNamedQuery("Direction.findByName", Direction.class)
				.setParameter("name", name).getSingleResult();
		}catch(NoResultException e){
			return null;
		}
		return foundresult;
	}

}
