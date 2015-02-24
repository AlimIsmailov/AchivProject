package com.ita.softserveinc.achiever.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ita.softserveinc.achiever.dao.IArticleDao;
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
@Service
@Transactional
public class ArticleServiceImpl implements IArticleService {

	private static final Logger LOG = LoggerFactory
			.getLogger(ArticleServiceImpl.class);

	@Autowired
	private IFileHandlerService fileHandlerService;

	@Autowired
	private IArticleDao articleDao;

	@Autowired
	private ISubtopicService subtopicService;

	@Override
	public void create(ArticleFormBean articleFormBean, Long id)
			throws ElementExistsException, InvalidFileTypeException,
			UploadFileOnServerException {

		String title = articleFormBean.getTitle();
		if (articleDao.findByTitle(title) != null) {
			throw new ElementExistsException();
		}

		String url = fileHandlerService.saveFileOnServer(
				articleFormBean.getFile(), title);

		if (url == null) {
			throw new UploadFileOnServerException();
		}

		Article article = new Article();
		article.setTitle(articleFormBean.getTitle());
		article.setDescription(articleFormBean.getDescription());
		article.setUrl(url);
		article.setSubtopic(subtopicService.findById(id));
		articleDao.create(article);
	}
	
	@Override
	public void delete(Long id) {
		Article article = findById(id);
		File file = new File(article.getUrl());
		if (file.delete()) {
			articleDao.delete(article);
		}
	}

	@Override
	public Article update(ArticleFormBean articleFormBean, Long id)
			throws ElementExistsException, RenameFileOnServerException {

		Article article = findById(id);
		String title = article.getTitle();
		String beanTitle = articleFormBean.getTitle();
		
		if (!title.equals(beanTitle)) {
			if(articleDao.findByTitle(beanTitle)!=null)
				LOG.info("Such file exists");
				throw new ElementExistsException();
		}

		article.setTitle(articleFormBean.getTitle());
		article.setDescription(articleFormBean.getDescription());
		
		return articleDao.update(article);
		
	}

	@Override
	public Article findById(Long id) {
		return articleDao.findById(Article.class, id);
	}

	@Override
	public List<Article> findAll() {
		return articleDao.findAll(Article.class);
	}

	@Override
	public Article findByTitle(String title) {
		return articleDao.findByTitle(title);
	}

}
