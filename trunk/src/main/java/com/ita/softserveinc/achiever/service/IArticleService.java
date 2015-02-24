package com.ita.softserveinc.achiever.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ita.softserveinc.achiever.entity.Article;
import com.ita.softserveinc.achiever.entity.Subtopic;
import com.ita.softserveinc.achiever.exception.ElementExistsException;

/**
 * 
 * @author Ostap Palianytsia
 * 
 */
@Component
public interface IArticleService {

	void create(Article article) throws ElementExistsException;

	Article update(Long id);

	void delete(Long id);

	Article findById(Long id);

	List<Article> findAll();

	Article findByTitle(String title);

	String saveFileOnServer(MultipartFile file, String title);

	void downloadFileFromServer(HttpServletResponse response, Long id);

}
