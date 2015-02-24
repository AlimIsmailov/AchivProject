package com.ita.softserveinc.achiever.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.exception.ElementExistsException;

/**
 * @author Yevgen Kopaev
 * 
 * @param <T>
 */
@Component
public interface IGenericService<T> {

	/**
	 * @param entity
	 * @throws Exception 

	 */
	void create(T entity) throws ElementExistsException;

	/**
	 * @param entity
	 * @return updated element
	 * @throws Exception 
	 */
	T update(T entity) throws ElementExistsException;

	/**
	 * @param entity
	 */
	void delete(T entity);

	/**
	 * @param id
	 */
	// TODO
	// void delete(Long id);

	/**
	 * @param id
	 * @return element
	 */
	T findById(Long id);

	/**
	 * @return list of elements
	 */
	List<T> findAll();

}
