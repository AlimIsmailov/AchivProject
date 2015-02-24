package com.ita.softserveinc.achiever.dao;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.ita.softserveinc.achiever.exception.ElementExistsException;

@Repository
public abstract class GenericDaoImpl<T> implements IGenericDao<T> {

	@PersistenceContext
	protected EntityManager entityManager;

	public void create(T entity) throws ElementExistsException {
		try{
			entityManager.persist(entity);
		}catch(EntityExistsException e){
			throw new ElementExistsException();
		}
	}

	public T update(T entity) {
		return entityManager.merge(entity);
	}

	public void delete(T entity) {
		entityManager.remove(entityManager.merge(entity));
	}

	public T findById(Class<T> entityClass, Long id) {
		return entityManager.find(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<T> entityClass) {
		List<T> result = null;
		TypedQuery<T> query = (TypedQuery<T>) entityManager.createQuery("from "
				+ entityClass.getName());
		result = query.getResultList();
		return result;

	}

}
