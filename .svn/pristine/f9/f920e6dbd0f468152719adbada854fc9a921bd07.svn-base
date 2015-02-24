package com.ita.softserveinc.achiever.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ita.softserveinc.achiever.entity.Article;
import com.ita.softserveinc.achiever.exception.ElementExistsException;
import com.ita.softserveinc.achiever.service.IArticleService;
import com.ita.softserveinc.achiever.service.ISubtopicService;

@Controller
@RequestMapping("/subtopics/{subtopicId}")
public class ArticleController {

	private static final Logger LOG = LoggerFactory
			.getLogger(ArticleController.class);

	@Autowired
	private IArticleService articleService;

	@Autowired
	private ISubtopicService subtopicService;

	@RequestMapping
	public String showSubtopicDetail(Model model,
			@PathVariable("subtopicId") Long id) {
		model.addAttribute("subtopic", subtopicService.findById(id));
		model.addAttribute("article", new Article());
		return "subtopic/subtopic-detail";
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String uploadFileHandler(@RequestParam("title") String title,
			@RequestParam("description") String description,
			@RequestParam("file") MultipartFile file, Model model,
			@PathVariable("subtopicId") Long id) {

		if (!file.isEmpty()) {
			String url = articleService.saveFileOnServer(file, title);

			if (url != null) {
				Article uploadedArticle = new Article();
				uploadedArticle.setTitle(title);
				uploadedArticle.setDescription(description);
				uploadedArticle.setUrl(url);
				uploadedArticle.setSubtopic(subtopicService.findById(id));
				try {
					articleService.create(uploadedArticle);
				} catch (ElementExistsException e) {
					return "redirect:/subtopics/{subtopicId}?fail=true";
				}
			}
		}
		return "redirect:/subtopics/{subtopicId}";
	}

	@RequestMapping("/download/{articleId}")
	public String downloadFileHandler(HttpServletResponse response,
			@PathVariable("articleId") Long id) {
		articleService.downloadFileFromServer(response, id);
		return "redirect:/subtopics/{subtopicId}";
	}

	@RequestMapping("/delete/{articleId}")
	public String deleteArticle(@PathVariable("articleId") Long id) {
		articleService.delete(id);
		return "redirect:/subtopics/{subtopicId}";
	}

	@RequestMapping("/edit/{articleId}")
	public String editArticle(@PathVariable("articleId") Long id) {
		articleService.update(id);
		return "redirect:/subtopics/{subtopicId}";
	}
}
