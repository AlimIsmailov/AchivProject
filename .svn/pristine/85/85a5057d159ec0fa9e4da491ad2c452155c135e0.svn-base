package com.ita.softserveinc.achiever.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.exception.ElementExistsException;

@Component
public interface IGenericDao<T> {

	void create(T entity);

	T update(T entity);

	void delete(T entity);

	T findById(Class<T> entityClass, Long id);

	List<T> findAll(Class<T> entityClass);
}
