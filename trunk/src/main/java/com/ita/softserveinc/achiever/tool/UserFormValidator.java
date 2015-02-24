package com.ita.softserveinc.achiever.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ita.softserveinc.achiever.entity.User;
import com.ita.softserveinc.achiever.service.IUserService;

@Component("userFormValidator")
public class UserFormValidator implements Validator {

	private static final Logger logger = LoggerFactory
			.getLogger(UserFormValidator.class);

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static final String LOGIN_NAME_PATTERN = "^[a-z0-9_-]{4,15}$";

	private static final String CPECIAL_CHARACTER_EXCLUDE_PATTERN = "^[^:>#*]+|([^:>#*][^>#*]+[^:>#*])$";

	private static final String PASSWORD_PATTERN = "^[A-Za-z0-9_#*$%-]{4,15}$";

	@Autowired
	private IUserService userService;
	
	private UserFormBean userFormBean;

	@Override
	public boolean supports(Class<?> supportClass) {
		return UserFormBean.class.isAssignableFrom(supportClass);
	}

	@Override
	public void validate(Object target, Errors errors) {

		userFormBean = (UserFormBean) target;

		User user = userFormBean.getUser();
		
		// check if user exist
		if (user.getId() == null) {
			
			logger.info("Check if login exist");
			rejectIfLoginExist(user.getLogin(), "user.login",
					"label.userLoginExist", errors);

			logger.info("Check if email exist");
			rejectIfEmailExist(user.getEmail(), "user.email",
					"label.userEmailExist", errors);
		}

		logger.info("Check if login match the pattern");
		rejectIfEmptyAndPatternMismatch(user.getLogin(), "user.login",
				"label.required.login", "label.pattern.mismatch.login",
				LOGIN_NAME_PATTERN, errors);

		logger.info("Check if first name match the pattern");
		rejectIfEmptyAndPatternMismatch(user.getFirstName(), "user.firstName",
				"label.required.firstName", "label.pattern.mismatch.firstName",
				CPECIAL_CHARACTER_EXCLUDE_PATTERN, errors);

		logger.info("Check if last name match the pattern");
		rejectIfEmptyAndPatternMismatch(user.getLastName(), "user.lastName",
				"label.required.lastName", "label.pattern.mismatch.lastName",
				CPECIAL_CHARACTER_EXCLUDE_PATTERN, errors);

		logger.info("Check if email match the pattern");
		if (!user.getEmail().equals(" ")) {
			if (!user.getEmail().matches(EMAIL_PATTERN)) {
				errors.rejectValue("user.email", "label.pattern.mismatch.email");
			}
		}

		logger.info("Check if password confirm and match the pattern");
		validatePassword(user.getPassword(), userFormBean.getConfirmPassword(),
				errors);
		logger.info("User errors " + errors.getAllErrors());

	}

	private void rejectIfEmptyAndPatternMismatch(String targetArg,
			String formField, String errorCodeEmpty,
			String errorCodePatternMismatch, String regex, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, formField,
				errorCodeEmpty);
		if (!errors.hasFieldErrors(formField)) {
			if (!targetArg.matches(regex)) {
				errors.rejectValue(formField, errorCodePatternMismatch);
			}
		}
	}

	public void rejectIfLoginExist(String targetArg, String formField,
			String errorLoginExist, Errors errors) {
		if (userService.findByLogin(targetArg) != null) {
			errors.rejectValue(formField, errorLoginExist);
		}
	}

	public void rejectIfEmailExist(String targetArg, String formField,
			String errorEmailExist, Errors errors) {
		if (userService.findByEmail(targetArg) != null) {
			errors.rejectValue(formField, errorEmailExist);
		}
	}

	private void validatePassword(String passwordArg, String retypePasswordArg,
			Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.password",
				"label.required.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
				"label.required.confirmPassword");
		if (!(errors.hasFieldErrors("user.password") || errors
				.hasFieldErrors("confirmPassword"))) {
			if (!passwordArg.matches(PASSWORD_PATTERN)) {
				errors.rejectValue("user.password",
						"label.pattern.mismatch.password");
			} else {
				if (!passwordArg.equals(retypePasswordArg)) {
					errors.rejectValue("confirmPassword",
							"label.confirmPasswordFail");
				}
			}
		}
	}

}
