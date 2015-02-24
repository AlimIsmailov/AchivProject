package com.ita.softserveinc.achiever.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.ita.softserveinc.achiever.controller.LoginController;

/**
 * @author Bogdan Rudka
 */

public class UrlAuthenticationSuccessHandler implements
		AuthenticationSuccessHandler {

	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	/**
	 * retains the role and relevant URL
	 */
	private Map<String, String> roles;

	public UrlAuthenticationSuccessHandler() {
		super();
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		handle(request, response, authentication);
		clearAuthenticationAttributes(request);

	}

	protected void handle(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException {
		logger.info("Authentifacation Provider start");
		String targetUrl = authentifacationProvider(authentication);
		if (response.isCommitted()) {
			logger.debug("Response has already been committed. Unable to redirect to "
					+ targetUrl);
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	/**
	 * 
	 * @param authentication
	 * @return
	 */
	protected String authentifacationProvider(Authentication authentication) {
		String targetUrl = null;
		Collection<? extends GrantedAuthority> authorities = null;

		authorities = authentication.getAuthorities();
		for (GrantedAuthority authority : authorities) {
			targetUrl = roles.get(authority.getAuthority());
			logger.info("Target URL is " + targetUrl + " Role id "
					+ authority.getAuthority());
			break;
		}
		return targetUrl;
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	public UrlAuthenticationSuccessHandler(String roleUser, String roleAdmin,
			String roleManager, String roleStudent) {
		super();
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRoles(Map<String, String> roles) {
		this.roles = roles;
	}

	public Map<String, String> getRoles() {
		return roles;
	}
}
