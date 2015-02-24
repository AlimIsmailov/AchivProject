package com.ita.softserveinc.achiever.dao;

import org.springframework.stereotype.Repository;

import com.ita.softserveinc.achiever.entity.UserAnswer;

/**
 * 
 * @author Kopaev Eugene
 *
 */
@Repository
public class UserAnswerDaoImpl extends GenericDaoImpl<UserAnswer> implements
		IUserAnswerDao {

	public UserAnswerDaoImpl() {
		super();
	}
}
