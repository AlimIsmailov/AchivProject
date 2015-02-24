package com.ita.softserveinc.achiever.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import org.joda.time.DateTime;

@Entity
@NamedQuery(name = "VerificationToken.findByToken", query = "SELECT t FROM VerificationToken t WHERE t.token = :token")
public class VerificationToken {

	private static final int DEFAULT_EXPIRY_TIME_IN_MINS = 60 * 24;

	@Id
	@GeneratedValue
	private Long id;

	private final String token;

	private Date expiryDate;

	private boolean verified;
	
	@Enumerated(EnumType.STRING)
	private VerificationTokenType tokenType;

	@ManyToOne
	@JoinColumn(name = "person_id")
	private User user;

	public VerificationToken() {
		this.token = UUID.randomUUID().toString();
		this.expiryDate = calculateExpiryDate(DEFAULT_EXPIRY_TIME_IN_MINS);

	}

	public VerificationToken(User user, VerificationTokenType tokenType) {
		this();
		this.user = user;
		this.tokenType = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public VerificationTokenType getTokenType() {
		return tokenType;
	}

	public void setTokenType(VerificationTokenType tokenType) {
		this.tokenType = tokenType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean hasExpired() {
		DateTime tokenDate = new DateTime(getExpiryDate());
		return tokenDate.isBeforeNow();
	}

	private Date calculateExpiryDate(int expiryTimeInMinutes) {
		DateTime now = new DateTime();
		return now.plusMinutes(expiryTimeInMinutes).toDate();
	}

	public enum VerificationTokenType {

		lostPassword, emailVerification, emailRegistration
	}

}
