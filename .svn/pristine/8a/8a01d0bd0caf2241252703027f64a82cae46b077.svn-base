package com.ita.softserveinc.achiever.dao;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ita.softserveinc.achiever.entity.Role;
import com.ita.softserveinc.achiever.entity.User;
import com.ita.softserveinc.achiever.tool.UserSearchRequestForm;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements IUserDao {

	@Autowired
	private IRoleDao roleDao;

	public User findByLogin(String login) {
		User foundresult = null;
		try {
			foundresult = (User) entityManager
					.createNamedQuery("User.findByLogin", User.class)
					.setParameter("login", login).getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			return null;
		}
		return foundresult;
	}

	public List<User> findByActiveStatus() {
		List<User> foundresult = null;
		try {
			foundresult = (List<User>) entityManager.createNamedQuery(
					"User.findByActiveStatus", User.class).getResultList();
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			return null;
		}
		return foundresult;
	}

	public List<User> findByFName(int maxResult) {
		List<User> fNameFinder = (List<User>) entityManager
				.createNamedQuery("User.findByFName", User.class)
				.setMaxResults(maxResult).getResultList();
		return fNameFinder;
	}

	public List<User> findByLName(int maxResult) {
		List<User> findByLName = (List<User>) entityManager
				.createNamedQuery("User.findByLName", User.class)
				.setMaxResults(maxResult).getResultList();
		return findByLName;
	}

	public List<User> findByActiveStatusResultOnPage(int pageMax) {
		List<User> foundresult = (List<User>) entityManager
				.createQuery(
						"SELECT u FROM User u ORDER BY u.active, u.login ASC",
						User.class).setMaxResults(pageMax).getResultList();
		return foundresult;
	}

	public List<User> findByActiveStatusPagination(int pagination, int maxResult) {
		List<User> pagingOnJSP = (List<User>) entityManager
				.createQuery(
						"SELECT u FROM User u ORDER BY u.active, u.login ASC",
						User.class).setFirstResult(pagination)
				.setMaxResults(maxResult).getResultList();
		return pagingOnJSP;
	}

	@Override
	public List<User> findByDateOfCreating(int maxResult) {
		List<User> foundUsersD = (List<User>) entityManager
				.createQuery("SELECT u FROM User u ORDER BY u.created DESC",
						User.class).setMaxResults(maxResult).getResultList();
		return foundUsersD;
	}

	@Override
	public List<User> findByDateOfCreating(int pagination, int maxResult) {
		List<User> usersAllOnPage = (List<User>) entityManager
				.createQuery("SELECT u FROM User u ORDER BY u.created DESC",
						User.class).setFirstResult(pagination)
				.setMaxResults(maxResult).getResultList();
		return usersAllOnPage;
	}

	public User findByEmail(String email) {
		User foundresult = null;
		try {
			foundresult = (User) entityManager
					.createNamedQuery("User.findByEmail", User.class)
					.setParameter("email", email).getSingleResult();
		} catch (PersistenceException e) {
			return null;
		}
		return foundresult;
	}

	@Override
	public void delete(User user) {
		user.setActive(0);
	}

	@Override
	public List<User> findAllByRole(String type) {
		List<User> foundUsers = null;
		try {
			foundUsers = entityManager
					.createNamedQuery("User.findAllByRoles", User.class)
					.setParameter("type", type).getResultList();
		} catch (PersistenceException e) {
			return null;
		}
		return foundUsers;

	}

	public List<User> findByGroup(String groupName) {
		List<User> foundUsers = null;
		try {
			foundUsers = entityManager
					.createNamedQuery("User.findByGroup", User.class)
					.setParameter("groupName", groupName).getResultList();
		} catch (PersistenceException e) {
			return null;
		}
		return foundUsers;
	}

	public List<User> findByGroup(String groupName, String roletype) {
		List<User> foundUsers = null;
		try {
			foundUsers = entityManager
					.createNamedQuery("User.findByGroupAndRole", User.class)
					.setParameter("groupName", groupName)
					.setParameter("type", roletype).getResultList();
		} catch (PersistenceException e) {
			return null;
		}
		return foundUsers;
	}

	@Override
	public List<User> findByRoleAndByGroup(String type, String groupName) {
		List<User> foundUsers = null;
		try {
			foundUsers = entityManager
					.createNamedQuery("User.findByRoleAndByGroup", User.class)
					.setParameter("groupName", groupName)
					.setParameter("type", type).getResultList();
		} catch (PersistenceException e) {
			return null;
		}
		return foundUsers;
	}

	@Override
	public List<User> findByLoginASC(int maxResult) {
		List<User> foundUsersD = null;
		foundUsersD = entityManager
				.createNamedQuery("User.findByLoginASC", User.class)
				.setMaxResults(maxResult).getResultList();
		return foundUsersD;
	}

	public List<User> findByUserRequest(UserSearchRequestForm searchRequest) {
		SearchRequestHandler searchRequestHandler = new SearchRequestHandler(searchRequest);
		return searchRequestHandler.getResultList();

	}
	
	private class SearchRequestHandler {
		private String firstName;
		private String login;
		private String email;
		private String lastName;
		private String statusOption;
		private String operationOnRoles;
		private Set<Role> selectedRoles;
		private Predicate mainPredicate = null;
		private Predicate rolesPredicate = null;
		private Predicate statusPredicate = null;
		private Predicate summary = null;
		private UserSearchRequestForm searchRequest;
		private CriteriaBuilder cb;
		private CriteriaQuery<User> cq;
		private Root<User> userRoot;

		public SearchRequestHandler(UserSearchRequestForm searchRequest) {
			firstName = searchRequest.getFirstNameOrLoginOrEmail();
			login = searchRequest.getFirstNameOrLoginOrEmail();
			email = searchRequest.getFirstNameOrLoginOrEmail();
			lastName = searchRequest.getSurName();
			statusOption = searchRequest.getStatusOption();
			operationOnRoles = searchRequest.getOperationOnRoles();
			selectedRoles = new HashSet<Role>();
			this.searchRequest = searchRequest;
			cb = entityManager.getCriteriaBuilder();
			cq = cb.createQuery(User.class);
			userRoot = cq.from(User.class);
			cq.select(userRoot);
			
			if (searchRequest.isAdvancedMode()) {
				for (Role role : searchRequest.getSelectedRoles()) {
					selectedRoles.add(roleDao.findByType(role.getType()));
				}
			}
		}

		public List<User> getResultList() {
			if (searchRequest.isSearchByName()) {
				mainPredicate = getMainPredicate();
			}

			if (!isRegularExpOrBlank(email) || !isRegularExpOrBlank(login)) {
				if (searchRequest.isSearchByLogin()) {
					mainPredicate = getEqualPredicate("login", login);
				}
				if (searchRequest.isSearchByEmail()) {
					mainPredicate = getEqualPredicate("email", email);
				}
			}

			if (searchRequest.isAdvancedMode()) {
				if (searchRequest.getSelectedRoles() == null) {
					return Collections.emptyList();
				}
				summary = applyAdvancedModeOptions();
				if (summary != null) {
					cq.where(summary);
				}
			} else if (mainPredicate != null)
				cq.where(mainPredicate);

			TypedQuery<User> q = entityManager.createQuery(cq);
			List<User> allUsers = q.getResultList();

			return allUsers;
		}

		private Predicate getMainPredicate() {
			if (isRegularExpOrBlank(firstName)) {
				return getPredicateForField("lastName", lastName);
			}
			if (isRegularExpOrBlank(lastName)) {
				return getPredicateForField("firstName", firstName);
			}

			return cb.and(cb.equal(userRoot.get("lastName"), lastName),
					cb.equal(userRoot.get("firstName"), firstName));
		}

		private Predicate getPredicateForField(String fieldName, String value) {
			if (isRegularExpOrBlank(value)) {
				return null;
			} else {
				return getEqualPredicate(fieldName, value);
			}
		}

		private Predicate getEqualPredicate(String fieldName, String valueArg) {
			return cb.equal(userRoot.get(fieldName), valueArg);
		}

		private boolean isRegularExpOrBlank(String arg) {
			return (StringUtils.isBlank(arg) || arg.equals("*"));
		}

		private Predicate applyAdvancedModeOptions() {
			Predicate result;

			if (selectedRoles.size() > 1) {
				int i = 0;
				for (Role role : selectedRoles) {
					if (i == 0)
						rolesPredicate = cb.isMember(role,
								userRoot.<Set<Role>> get("roles"));
					else {
						if (operationOnRoles.equals("AND")) {
							rolesPredicate = cb.and(
									rolesPredicate,
									cb.isMember(role,
											userRoot.<Set<Role>> get("roles")));
						}

						if (operationOnRoles.equals("OR")) {
							rolesPredicate = cb.or(
									rolesPredicate,
									cb.isMember(role,
											userRoot.<Set<Role>> get("roles")));
						}
					}
					i++;
				}
			} else {
				rolesPredicate = cb.isMember(selectedRoles.iterator().next(),
						userRoot.<Set<Role>> get("roles"));
			}
			result = applyStatusOption();
			return result;
		}

		private Predicate applyStatusOption() {
			Predicate result;
			
			if (!statusOption.equals("any")) {
				statusPredicate = cb.equal(userRoot.get("active"),
						Integer.parseInt(statusOption));

				if (mainPredicate == null) {
					result = cb.and(statusPredicate, rolesPredicate);
				} else {
					result = cb.and(statusPredicate, mainPredicate,
							rolesPredicate);
				}
			} else if (mainPredicate == null) {
				result = rolesPredicate;
			} else {
				result = cb.and(mainPredicate, rolesPredicate);
			}
			return result;
		}
	}
}
