package com.ita.softserveinc.achiever.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ita.softserveinc.achiever.entity.User;

public class UserFormBean {
	
	private User user;
	private String confirmPassword;
	private static final Logger logger = LoggerFactory
			.getLogger(UserFormBean.class);
	public UserFormBean() {
	}
	
	public UserFormBean(User user) {
		logger.info("Constructor:User password "+user.getPassword());
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		logger.info("setUser:User password "+user.getPassword());
		this.user = user;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
}
