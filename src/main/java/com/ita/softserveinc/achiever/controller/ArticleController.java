package com.ita.softserveinc.achiever.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ita.softserveinc.achiever.exception.ElementExistsException;
import com.ita.softserveinc.achiever.exception.InvalidFileTypeException;
import com.ita.softserveinc.achiever.exception.RenameFileOnServerException;
import com.ita.softserveinc.achiever.exception.UploadFileOnServerException;
import com.ita.softserveinc.achiever.service.IArticleService;
import com.ita.softserveinc.achiever.service.ISubtopicService;
import com.ita.softserveinc.achiever.service.IFileHandlerService;
import com.ita.softserveinc.achiever.tool.ArticleFormBean;

/**
 * 
 * @author Ostap Palianytsia
 * 
 */
@Controller
@RequestMapping("/subtopics/{subtopicId}")
public class ArticleController {

	@Autowired
	private IArticleService articleService;

	@Autowired
	private ISubtopicService subtopicService;
	
	@Autowired
	private IFileHandlerService fileHandlerService;

	@RequestMapping
	public String showSubtopicDetail(Model model,
			@PathVariable("subtopicId") Long id) {
		model.addAttribute("subtopic", subtopicService.findById(id));
		model.addAttribute("articleFormBean", new ArticleFormBean());
		return "subtopic/subtopic-detail";
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String uploadFileHandler(
			@Valid @ModelAttribute("articleFormBean") ArticleFormBean articleFormBean,
			BindingResult result, @PathVariable("subtopicId") Long id,
			RedirectAttributes redirectAttributes) {

		MultipartFile file = articleFormBean.getFile();

		if (result.hasErrors() || file.isEmpty()) {
			redirectAttributes.addFlashAttribute("mistakes", true);
			return "redirect:/subtopics/{subtopicId}";
		}

		try {
			articleService.create(articleFormBean, id);
		} catch (ElementExistsException e) {
			redirectAttributes.addFlashAttribute("exists", true);
		} catch (InvalidFileTypeException e) {
			redirectAttributes.addFlashAttribute("upload", false);
		} catch (UploadFileOnServerException e) {
			redirectAttributes.addFlashAttribute("server", false);
		}
		return "redirect:/subtopics/{subtopicId}";
	}

	@RequestMapping(value = "/edit/{articleId}", method = RequestMethod.POST)
	public String editArticle(
			@Valid @ModelAttribute("articleFormBean") ArticleFormBean articleFormBean,
			BindingResult result, RedirectAttributes redirectAttributes,
			@PathVariable("articleId") Long id) {

		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("mistakes", true);
			return "subtopics/{subtopicId}";
		}

		try {
			articleService.update(articleFormBean, id);
		} catch (ElementExistsException e) {
			redirectAttributes.addFlashAttribute("exists", true);
			//return "subtopics/{subtopicId}";
		} catch (InvalidFileTypeException e) {
			redirectAttributes.addFlashAttribute("upload", false);
			//return "subtopics/{subtopicId}";
		} catch (RenameFileOnServerException e) {
			redirectAttributes.addFlashAttribute("rename", false);
			//return "subtopics/{subtopicId}";
		}
		return "redirect:/subtopics/{subtopicId}";
	}

	@RequestMapping("/download/{articleId}")
	public void downloadFileHandler(HttpServletResponse response,
			@PathVariable("articleId") Long id) {
		fileHandlerService.downloadFileFromServer(response, id);
	}

	@RequestMapping("/delete/{articleId}")
	public String deleteArticle(@PathVariable("articleId") Long id) {
		articleService.delete(id);
		return "redirect:/subtopics/{subtopicId}";
	}
}
