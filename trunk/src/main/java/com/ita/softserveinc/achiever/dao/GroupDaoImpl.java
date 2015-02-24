package com.ita.softserveinc.achiever.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ita.softserveinc.achiever.entity.Direction;
import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.entity.User;

@Repository
public class GroupDaoImpl extends GenericDaoImpl<Group> implements IGroupDao {
	private static final Logger LOG = Logger.getLogger(GroupDaoImpl.class);

	@Override
	public Group findByName(String name) {
		Group group = null;
		try {
			group = entityManager
					.createNamedQuery("Group.findByName", Group.class)
					.setParameter("name", name).getSingleResult();

		} catch (PersistenceException e) {
			return null;
		}
		return group;
	}

	@Override
	public List<Group> findByDirection(Direction direction) {
		List<Group> groups = new ArrayList<Group>();
		try {
			groups = entityManager
					.createNamedQuery("Group.findByDirection", Group.class)
					.setParameter("direction", direction).getResultList();

		} catch (PersistenceException e) {
			return new ArrayList<Group>();

		}
		return groups;

	}

	@Override
	public List<Group> findByUser(User manager) {
		List<Group> foundGroups = new ArrayList<Group>();
		try {
			foundGroups = entityManager
					.createNamedQuery("Group.findByUser", Group.class)
					.setParameter("login", manager.getLogin()).getResultList();
		} catch (PersistenceException e) {
			return new ArrayList<Group>();
		}
		return foundGroups;
	}

	@Override
	public List<Group> findStartAfterDate(Date date) {
		List<Group> foundGroups = new ArrayList<Group>();
		try {
			foundGroups = entityManager
					.createNamedQuery("Group.findStartAfterDate", Group.class)
					.setParameter("start", date).getResultList();
		} catch (PersistenceException e) {
			return new ArrayList<Group>();
		}
		return foundGroups;
	}

	@Override
	public List<Group> findEndBeforeDate(Date date) {
		List<Group> foundGroups = new ArrayList<Group>();
		try {
			foundGroups = entityManager
					.createNamedQuery("Group.findEndBeforeDate", Group.class)
					.setParameter("end", date).getResultList();
		} catch (PersistenceException e) {
			return new ArrayList<Group>();
		}
		return foundGroups;
	}

	@Override
	public List<Group> findStartAfterDateByUser(Date date, String login) {
		List<Group> foundGroups = new ArrayList<Group>();
		try {
			foundGroups = entityManager
					.createNamedQuery("Group.findStartAfterDateByUser",
							Group.class).setParameter("start", date)
					.setParameter("login", login).getResultList();
		} catch (PersistenceException e) {
			return new ArrayList<Group>();
		}
		return foundGroups;
	}

	@Override
	public List<Group> findEndBeforeDateByUser(Date date, String login) {
		List<Group> foundGroups = new ArrayList<Group>();
		try {
			foundGroups = entityManager
					.createNamedQuery("Group.findEndBeforeDateByUser",
							Group.class).setParameter("end", date)
					.setParameter("login", login).getResultList();
		} catch (PersistenceException e) {
			return new ArrayList<Group>();
		}
		return foundGroups;
	}

	@Override
	public List<Group> findCurrentGroups(Date today) {
		List<Group> foundGroups = new ArrayList<Group>();
		try {
			foundGroups = entityManager
					.createNamedQuery("Group.findCurrent", Group.class)
					.setParameter("start", today).setParameter("end", today)
					.getResultList();
		} catch (PersistenceException e) {
			return new ArrayList<Group>();
		}
		return foundGroups;
	}

	@Override
	public List<Group> findCurrentGroupsByUser(Date today, String login) {
		List<Group> foundGroups = new ArrayList<Group>();
		try {
			foundGroups = entityManager
					.createNamedQuery("Group.findCurrentByUser", Group.class)
					.setParameter("start", today).setParameter("end", today)
					.setParameter("login", login).getResultList();
		} catch (PersistenceException e) {
			return new ArrayList<Group>();
		}
		return foundGroups;
	}

	@Override
	public List<Group> findByStartDate(Date date) {
		List<Group> foundGroups = new ArrayList<Group>();
		try {
			foundGroups = entityManager
					.createNamedQuery("Group.findByStartDate", Group.class)
					.setParameter("start", date).getResultList();
		} catch (PersistenceException e) {
			return new ArrayList<Group>();
		}
		return foundGroups;
	}

	@Override
	public List<Group> findByEndDate(Date date) {
		List<Group> foundGroups = new ArrayList<Group>();
		try {
			foundGroups = entityManager
					.createNamedQuery("Group.findByEndDate", Group.class)
					.setParameter("end", date).getResultList();
		} catch (PersistenceException e) {
			return new ArrayList<Group>();
		}
		return foundGroups;
	}

}
