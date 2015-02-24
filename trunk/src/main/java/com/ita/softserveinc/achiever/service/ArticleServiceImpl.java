package com.ita.softserveinc.achiever.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ita.softserveinc.achiever.dao.IArticleDao;
import com.ita.softserveinc.achiever.dao.ISubtopicDao;
import com.ita.softserveinc.achiever.entity.Article;
import com.ita.softserveinc.achiever.entity.Subtopic;
import com.ita.softserveinc.achiever.exception.ArticleUploadException;
import com.ita.softserveinc.achiever.exception.ElementExistsException;

/**
 * 
 * @author Ostap Palianytsia
 * 
 */
@Service
@Transactional
public class ArticleServiceImpl implements IArticleService {

	private static final int BUFFER_SIZE = 4096;
	private static final String PDF_MIME_TYPE = "application/pdf";

	private static final Logger LOG = LoggerFactory
			.getLogger(ArticleServiceImpl.class);

	@Autowired
	private IArticleDao articleDao;

	@Autowired
	private ISubtopicDao subtopicDao;

	@Override
	public void create(Article article) throws ElementExistsException {
		if(articleDao.findByTitle(article.getTitle()) != null) {
			throw new ElementExistsException();
		}
		articleDao.create(article);
	}

	@Override
	public Article update(Long id) {
		Article article = findById(id);
		
		return articleDao.update(article);
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

	@Override
	public String saveFileOnServer(MultipartFile article, String title) {

		validateFile(article);

		String url = null;
		FileOutputStream fileOutputStream = null;
		BufferedOutputStream bufferedOutputStream = null;

		try {
			byte[] bytes = article.getBytes();

			File dir = new File(System.getProperty("catalina.home")
					+ File.separator + "Articles");
			if (!dir.exists()) {
				dir.mkdirs();
			}

			File serverFile = new File(dir.getAbsolutePath() + File.separator
					+ title);
			fileOutputStream = new FileOutputStream(serverFile);
			bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
			bufferedOutputStream.write(bytes);

			url = serverFile.getAbsolutePath();

			LOG.info("File saved successfuly. Server File Location="
					+ url);

			return url;

		} catch (IOException e) {
			LOG.info("File saving failed");
			return null;
		} finally {
			try {
				bufferedOutputStream.close();
				fileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void validateFile(MultipartFile article) {
		if (!article.getContentType().equals(PDF_MIME_TYPE)) {
			throw new ArticleUploadException(
					"Allows only downloading of .pdf files");
		}
	}

	@Override
	public void downloadFileFromServer(HttpServletResponse response, Long id) {
		Article article = findById(id);
		String url = article.getUrl();
		File downloadFile = new File(url);
		FileInputStream fileInputStream = null;
		OutputStream outputStream = null;
		
		try {
			fileInputStream = new FileInputStream(downloadFile);
			response.setContentType(PDF_MIME_TYPE);
			outputStream = response.getOutputStream();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;

		try {
			while ((bytesRead = fileInputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileInputStream.close();
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

	}

}
