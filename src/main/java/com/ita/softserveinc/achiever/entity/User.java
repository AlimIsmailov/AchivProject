package com.ita.softserveinc.achiever.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.common.base.Objects;
import com.ita.softserveinc.achiever.entity.VerificationToken.VerificationTokenType;

/**
 * 
 * @author Bogdan Rudka
 */

@NamedQueries({
		@NamedQuery(name = "User.findByLogin", query = "SELECT t FROM User t WHERE t.login = :login"),
		@NamedQuery(name = "User.findByActiveStatus", query = "SELECT u FROM User u ORDER BY u.active, u.login ASC"),
		@NamedQuery(name = "User.findByEmail", query = "SELECT t FROM User t WHERE t.email = :email"),
		@NamedQuery(name = "User.findAllByRoles", query = "SELECT u FROM User u JOIN u.roles r WHERE r.type = :type"),
		@NamedQuery(name = "User.findByGroup", query = "SELECT u FROM User u JOIN u.groups g WHERE g.groupName = :groupName"),
		@NamedQuery(name = "User.findByGroupAndRole", query = "SELECT u FROM User u JOIN u.groups g JOIN u.roles r WHERE g.groupName = :groupName AND r.type = :type"),
		@NamedQuery(name = "User.findByRoleAndByGroup", query = "SELECT u FROM User u JOIN u.roles r JOIN u.groups g WHERE r.type = :type  AND g.groupName = :groupName"),
		@NamedQuery(name = "User.findByDateOfCreating", query = "SELECT u FROM User u ORDER BY u.created DESC"),
		@NamedQuery(name = "User.findByLoginASC", query = "SELECT u FROM User u ORDER BY u.login ASC"),
		@NamedQuery(name = "User.findByFName", query = "SELECT u FROM User u ORDER BY u.firstName ASC"),
		@NamedQuery(name = "User.findByLName", query = "SELECT u FROM User u ORDER BY u.lastName ASC") })
@Entity
@Table(name = "USERS")
public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Basic(optional = false)
	@Column(name = "LOGIN", unique = true, nullable = false, updatable = false)
	private String login;

	@Column(name = "PASSWORD", nullable = false)
	@JsonIgnore
	private String password;

	@Column(name = "EMAIL", unique = true, nullable = false)
	private String email;

	@Basic(optional = false)
	@ManyToMany
	@JoinTable(name = "USERS_to_ROLES", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	@JsonIgnore
	private Set<Role> roles = new HashSet<Role>();

	@ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Group> groups = new HashSet<Group>();

	@Column(name = "ACTIVE", nullable = false)
	@JsonIgnore
	private Byte active;

	@OneToMany(mappedBy = "user", orphanRemoval = true)
	@JsonManagedReference
	private List<QuizResult> results;

	@OneToOne(mappedBy = "user")
	@JsonIgnore
	private Application application;

	@Basic(fetch = FetchType.LAZY)
	@Column(name = "IMAGE")
	@Lob
	@JsonIgnore
	private byte[] image;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, targetEntity = VerificationToken.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
	private List<VerificationToken> verificationTokens = new ArrayList<VerificationToken>();

	@Column(name = "VERIFIED")
	@JsonIgnore
	private boolean verified;

	public User() {
		super();
		this.active = 0;
		this.verified = false;
	}

	public User(String login, String password, String email) {
		this();
		this.login = login;
		this.password = password;
		this.email = email;
	}

	/**
	 * @return the name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {

		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {

		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {

		this.email = email;
	}

	public List<QuizResult> getResults() {
		return Collections.unmodifiableList(this.results);
	}

	public void setResults(List<QuizResult> results) {
		this.results = results;
	}

	/**
	 * @return the role
	 */
	public Set<Role> getRoles() {
		return Collections.unmodifiableSet(this.roles);
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Byte getActive() {
		return active;
	}

	public void setActive(Byte active) {
		if (active == null)
			this.active = 0;
		else
			this.active = active;
	}

	public void setActive(Integer active) {
		if (active != null)
			this.active = active.byteValue();
		else
			this.active = 0;
	}

	public void addRole(Role role) {
		roles.add(role);
	}

	public void removeRole(Role role) {
		roles.remove(role);
	}

	public void addGroup(Group group) {
		this.groups.add(group);
	}

	public Set<Group> getGroups() {
		return Collections.unmodifiableSet(groups);
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public synchronized List<VerificationToken> getVerificationTokens() {
		return Collections.unmodifiableList(this.verificationTokens);
	}

	public synchronized void addVerificationToken(VerificationToken token) {
		this.verificationTokens.add(token);

	}

	public VerificationToken getActiveEmailRegistrationToken() {
		return getActiveToken(VerificationTokenType.emailRegistration);
	}

	public VerificationToken getActiveEmailVerificationToken() {
		return getActiveToken(VerificationTokenType.emailVerification);
	}

	public VerificationToken getActiveLostPasswordToken() {
		return getActiveToken(VerificationTokenType.lostPassword);
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	
	public String getMainRole() {
		
		String mainRole = "User";
		
		if (this.roles.contains(new Role("ROLE_ADMIN"))) {
			return "Administrator";
		}
		
		if (this.roles.contains(new Role("ROLE_MANAGER"))) {
			return "Manager";
		}
		
		if (this.roles.contains(new Role("ROLE_STUDENT"))) {
			return "Student";
		}
		
		return mainRole;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.login);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		final User other = (User) obj;
		return Objects.equal(this.login, other.login);
	}

	@Override
	public String toString() {

		return Objects.toStringHelper(this).add("login", this.login)
				.add("first name", this.firstName)
				.add("last name", this.lastName).add("email", this.email)
				.toString();

	}

	private VerificationToken getActiveToken(VerificationTokenType tokenType) {
		VerificationToken activeToken = null;
		for (VerificationToken token : getVerificationTokens()) {
			if (token.getTokenType().equals(tokenType) && !token.hasExpired()
					&& !token.isVerified()) {
				activeToken = token;
				break;
			}
		}

		return activeToken;
	}

	
}