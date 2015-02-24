package com.ita.softserveinc.achiever.dao;

import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.entity.Article;

/**
 * 
 * @author Ostap Palianytsia
 * 
 */
@Component
public interface IArticleDao extends IGenericDao<Article> {

	Article findByTitle(String title);
}
