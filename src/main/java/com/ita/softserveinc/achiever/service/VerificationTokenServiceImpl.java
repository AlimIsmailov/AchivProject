package com.ita.softserveinc.achiever.service;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.ita.softserveinc.achiever.api.EmailVerificationRequest;
import com.ita.softserveinc.achiever.api.PasswordRequest;
import com.ita.softserveinc.achiever.dao.IVerificationTokenDao;
import com.ita.softserveinc.achiever.entity.User;
import com.ita.softserveinc.achiever.entity.VerificationToken;
import com.ita.softserveinc.achiever.entity.VerificationToken.VerificationTokenType;
import com.ita.softserveinc.achiever.exception.AlreadyVerifiedException;
import com.ita.softserveinc.achiever.exception.TokenHasExpiredException;
import com.ita.softserveinc.achiever.exception.TokenNotFoundException;
import com.ita.softserveinc.achiever.exception.UserNotFoundException;
import com.ita.softserveinc.achiever.tool.EmailServiceTokenModel;

@Service
public class VerificationTokenServiceImpl implements IVerificationTokenService {

	@Value("#{emailProperties['hostNameUrl']}")
	private String hostNameUrl;

	private final IVerificationTokenDao verificationTokenDao;

	private final IUserService userService;

	private final IMailSenderService mailSender;

	@Autowired
	public VerificationTokenServiceImpl(
			IVerificationTokenDao verificationTokenDao,
			IUserService userService, IMailSenderService mailSender) {

		this.verificationTokenDao = verificationTokenDao;
		this.userService = userService;
		this.mailSender = mailSender;

	}

	@Transactional
	public VerificationToken sendEmailRegistrationToken(
			EmailVerificationRequest emailVerificationRequest) {
		User user = ensureUserExist(emailVerificationRequest.getEmail());
		VerificationToken token = generateActiveToken(
				VerificationTokenType.emailRegistration, user);
		mailSender.sendRegistrationEmail(new EmailServiceTokenModel(user,
				token, hostNameUrl));
		return token;
	}

	@Transactional
	public VerificationToken sendEmailVerificationToken(
			EmailVerificationRequest emailVerificationRequest) {
		User user = ensureUserExist(emailVerificationRequest.getEmail());
		user.setVerified(false);
		VerificationToken token = generateActiveToken(
				VerificationTokenType.emailVerification, user);
		mailSender.sendVerificationEmail(new EmailServiceTokenModel(user,
				token, hostNameUrl));
		return token;
	}

	@Transactional
	public VerificationToken sendLostPasswordToken(
			EmailVerificationRequest emailVerificationRequest) {
		User user = ensureUserExist(emailVerificationRequest.getEmail());
		VerificationToken token = generateActiveToken(
				VerificationTokenType.lostPassword, user);
		mailSender.sendLostPasswordEmail(new EmailServiceTokenModel(user,
				token, hostNameUrl));
		return token;

	}

	@Transactional
	public VerificationToken verifyToken(String base64EncodedToken) {
		Assert.notNull(base64EncodedToken);
		VerificationToken token = loadToken(base64EncodedToken);
		if (token.isVerified() || token.getUser().isVerified()) {
			throw new AlreadyVerifiedException();
		}

		User user = token.getUser();
		token.setVerified(true);
		user.setVerified(true);
		userService.update(user);

		return token;

	}

	@Transactional
	public VerificationToken resetPassword(String base64EncodedToken,
			PasswordRequest passwordRequest) {
		Assert.notNull(base64EncodedToken);

		VerificationToken token = loadToken(base64EncodedToken);

		if (token.isVerified()) {
			throw new AlreadyVerifiedException();
		}

		token.setVerified(true);

		String login = token.getUser().getLogin();
		userService.updatePassword(login, passwordRequest);

		return token;

	}

	private VerificationToken generateActiveToken(
			VerificationTokenType tokenType, User user) {

		VerificationToken token = null;

		switch (tokenType) {

		case emailRegistration:
			token = user.getActiveEmailRegistrationToken();
			break;
		case emailVerification:
			token = user.getActiveEmailVerificationToken();
		case lostPassword:
			token = user.getActiveLostPasswordToken();
		}

		if (token == null) {
			token = createToken(tokenType, user);
		}

		return token;
	}

	private VerificationToken createToken(VerificationTokenType tokenType,
			User user) {

		VerificationToken token = new VerificationToken(user, tokenType);
		user.addVerificationToken(token);
		userService.update(user);

		return token;
	}

	private User ensureUserExist(String email) {
		User user = userService.findByEmail(email);

		if (user == null) {
			throw new UserNotFoundException();
		}

		return user;
	}

	private VerificationToken loadToken(String base64EncodedToken)
			throws TokenNotFoundException, TokenHasExpiredException {

		Assert.notNull(base64EncodedToken);

		String rawToken = new String(Base64.decodeBase64(base64EncodedToken));
		VerificationToken token = verificationTokenDao.findByToken(rawToken);

		if (token == null) {
			throw new TokenNotFoundException();
		}

		if (token.hasExpired()) {
			throw new TokenHasExpiredException();
		}

		return token;
	}

}
