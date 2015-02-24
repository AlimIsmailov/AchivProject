package com.ita.softserveinc.achiever.dao;

import org.springframework.stereotype.Repository;

import com.ita.softserveinc.achiever.entity.VerificationToken;

@Repository
public class VerificationTokenDaoImpl extends GenericDaoImpl<VerificationToken>
		implements IVerificationTokenDao {

	public VerificationToken findByToken(String token) {
		VerificationToken tokenEntity = null;

		tokenEntity = entityManager
				.createNamedQuery("VerificationToken.findByToken",
						VerificationToken.class).setParameter("token", token)
				.getSingleResult();
		return tokenEntity;
	}

}
