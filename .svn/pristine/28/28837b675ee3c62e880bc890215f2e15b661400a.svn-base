package com.ita.softserveinc.achiever.tool;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.ita.softserveinc.achiever.entity.Article;

/**
 * 
 * @author Ostap Palianytsia
 * 
 */
public class ArticleFormBean {

	@Valid
	private Article article;

	@Size(min = 1, max = 30, message = "Title's size must be between 1 and 30 characters")
	private String title;

	@Size(min = 10, max = 255, message = "Don't be lazy, write down some description about article")
	private String description;

	private MultipartFile file;

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
