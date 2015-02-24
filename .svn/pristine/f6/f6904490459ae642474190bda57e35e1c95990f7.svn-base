package com.ita.softserveinc.achiever.dao;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.entity.Location;

@Repository
public class LocationDaoImpl extends GenericDaoImpl<Location> implements
		ILocationDao {
	private static final Logger LOG = Logger.getLogger(GroupDaoImpl.class);

	@Override
	public Location findByName(String name) {
		Location location = null;
		try {
			location = entityManager
					.createNamedQuery("Location.findByName", Location.class)
					.setParameter("name", name).getSingleResult();

		} catch (NoResultException e) {
			LOG.info("No one class with this name was found");
			return null;
		}
		return location;
	}
}