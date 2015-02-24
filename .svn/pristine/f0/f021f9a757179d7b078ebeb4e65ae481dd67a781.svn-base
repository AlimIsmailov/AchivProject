package com.ita.softserveinc.achiever.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ita.softserveinc.achiever.api.EmailVerificationRequest;
import com.ita.softserveinc.achiever.service.IUserService;

@Component("emailAddressValidator")
public class EmailAddressValidator implements Validator {
	
	@Autowired
	private IUserService userService;

	@Override
	public boolean supports(Class<?> clazz) {

		return EmailVerificationRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		EmailVerificationRequest emailVerificationRequest = (EmailVerificationRequest) target;
		
		String emailAddress= emailVerificationRequest.getEmail();
		
		if (emailAddress == null) {
			return;
		}
		
		if (userService.findByEmail(emailAddress) != null) {
			errors.rejectValue("email", "label.userEmailExist");
		}

	}

}
