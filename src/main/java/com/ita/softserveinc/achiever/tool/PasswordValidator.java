package com.ita.softserveinc.achiever.tool;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ita.softserveinc.achiever.api.PasswordRequest;

@Component("passwordValidator")
public class PasswordValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return PasswordRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		PasswordRequest passwordRequest = (PasswordRequest) target;
		
		String password = passwordRequest.getPassword();
		String confirmPassword = passwordRequest.getConfirmPassword();
		
		if (!password.equals(confirmPassword)) {
			errors.rejectValue("confirmPassword", "label.confirmPasswordFail");
		}
	}

}
