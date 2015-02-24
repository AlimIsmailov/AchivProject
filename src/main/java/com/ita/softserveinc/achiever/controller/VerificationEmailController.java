package com.ita.softserveinc.achiever.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.softserveinc.achiever.api.EmailVerificationRequest;
import com.ita.softserveinc.achiever.api.PasswordRequest;
import com.ita.softserveinc.achiever.exception.AlreadyVerifiedException;
import com.ita.softserveinc.achiever.service.IVerificationTokenService;

@Controller
public class VerificationEmailController {

	private static final Logger LOG = LoggerFactory
			.getLogger(VerificationEmailController.class);

	private final IVerificationTokenService verificationTokenService;

	private final Validator passwordValidator;

	@Autowired
	public VerificationEmailController(
			IVerificationTokenService verificationTokenService,
			@Qualifier("passwordValidator") Validator passwordValidator) {

		this.verificationTokenService = verificationTokenService;
		this.passwordValidator = passwordValidator;
	}

	@RequestMapping("/verify/registration/{token}")
	public String verifyRegistrationEmailLink(@PathVariable String token) {

		try {
			verificationTokenService.verifyToken(token);
		} catch (AlreadyVerifiedException ex) {
			LOG.info(ex.getMessage());
		}

		return "redirect:/activation";

	}

	@RequestMapping(value = "/verify/email/{token}")
	public String verifyVerificationEmailLink(@PathVariable String token) {

		try {
			verificationTokenService.verifyToken(token);
		} catch (AlreadyVerifiedException ex) {
			LOG.info(ex.getMessage());
		}

		return "user/success-email-verification";
	}

	@RequestMapping(value = "/reset/password/{token}")
	public String verifyForgotPasswordLink(Model model) {

		model.addAttribute("password", new PasswordRequest());

		return "user/reset-password";
	}

	@RequestMapping(value = "/reset/password/{token}", method = RequestMethod.POST)
	public String resetPassword(@PathVariable String token,
			@Valid @ModelAttribute("password") PasswordRequest passwordRequest,
			BindingResult bindingResult) {
		passwordValidator.validate(passwordRequest, bindingResult);

		if (bindingResult.hasErrors()) {
			return "user/reset-password";
		}

		try {
			verificationTokenService.resetPassword(token, passwordRequest);
		} catch (AlreadyVerifiedException ex) {
			LOG.info(ex.getMessage());
		}
		return "user/success-change-password";

	}

	@RequestMapping(value = "send/forgotPassword", method = RequestMethod.POST)
	public String sendForgotPasswordEmail(@RequestParam String email) {
		Assert.notNull(email);

		verificationTokenService
				.sendLostPasswordToken(new EmailVerificationRequest(email));

		return "user/send-lost-password";
	}

}
