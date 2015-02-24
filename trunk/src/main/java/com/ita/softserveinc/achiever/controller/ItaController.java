/**
 * 
 */
package com.ita.softserveinc.achiever.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Taras Hrytsko
 *
 */
@Controller
public class ItaController {

	/**
	 * @return home page
	 */
	@RequestMapping("/home")
	public String indexHome() {

		return "home";
	}

	/**
	 * @return homepage
	 */
	@RequestMapping("/")
	public String home() {
		return "redirect:/home";
	}

}
