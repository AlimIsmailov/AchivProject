package com.ita.softserveinc.achiever.tool;

import java.io.Serializable;

import org.apache.commons.codec.binary.Base64;

import com.ita.softserveinc.achiever.entity.User;
import com.ita.softserveinc.achiever.entity.VerificationToken;
import com.ita.softserveinc.achiever.entity.VerificationToken.VerificationTokenType;


public class EmailServiceTokenModel implements Serializable {

	private static final long serialVersionUID = -8654194854266130431L;

	private final String token;
	private final String email;
	private final String hostNameUrl;
	private final VerificationTokenType tokenType;

	public EmailServiceTokenModel(User user, VerificationToken token,
			String hostNameUrl) {

		this.token = token.getToken();
		this.email = user.getEmail();
		this.hostNameUrl = hostNameUrl;
		this.tokenType = token.getTokenType();
	}

	public String getEncodedToken() {
		return new String(Base64.encodeBase64(token.getBytes()));
	}

	public String getToken() {
		return token;
	}

	public String getEmail() {
		return email;
	}

	public String getHostNameUrl() {
		return hostNameUrl;
	}

	public VerificationTokenType getTokenType() {
		return tokenType;
	}

}
