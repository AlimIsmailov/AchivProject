package com.ita.softserveinc.achiever.service;

import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.api.EmailVerificationRequest;
import com.ita.softserveinc.achiever.api.PasswordRequest;
import com.ita.softserveinc.achiever.entity.VerificationToken;

@Component
public interface IVerificationTokenService {

	VerificationToken sendEmailRegistrationToken(
			EmailVerificationRequest emailVerificationRequest);

	VerificationToken sendEmailVerificationToken(
			EmailVerificationRequest emailVerificationRequest);

	VerificationToken sendLostPasswordToken(
			EmailVerificationRequest emailVerificationRequest);

	VerificationToken verifyToken(String base64EncodedToken);

	VerificationToken resetPassword(String base64EncodedToken,
			PasswordRequest passwordRequest);

}
