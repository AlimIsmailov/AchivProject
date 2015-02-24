package com.ita.softserveinc.achiever.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ita.softserveinc.achiever.entity.Article;

/**
 * 
 * @author Ostap Palianytsia
 * 
 */
@Repository
public class ArticleDaoImpl extends GenericDaoImpl<Article> implements
		IArticleDao {

	@Override
	public Article findByTitle(String title) {
		Article article = null;
		try {
			Query query = entityManager.createNamedQuery("Article.getByTitle", Article.class)
					.setParameter("title", title);
			article = (Article) query.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
		return article;
	}

}
