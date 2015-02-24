package com.ita.softserveinc.achiever.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.entity.Article;
import com.ita.softserveinc.achiever.exception.ElementExistsException;
import com.ita.softserveinc.achiever.exception.InvalidFileTypeException;
import com.ita.softserveinc.achiever.exception.RenameFileOnServerException;
import com.ita.softserveinc.achiever.exception.UploadFileOnServerException;
import com.ita.softserveinc.achiever.tool.ArticleFormBean;

/**
 * 
 * @author Ostap Palianytsia
 * 
 */
@Component
public interface IArticleService {

	void create(ArticleFormBean articleFormBean, Long id)
			throws ElementExistsException, InvalidFileTypeException,
			UploadFileOnServerException;

	Article update(ArticleFormBean articleFormBean, Long id)
			throws ElementExistsException, InvalidFileTypeException,
			RenameFileOnServerException;

	void delete(Long id);

	Article findById(Long id);

	List<Article> findAll();

	Article findByTitle(String title);

}
