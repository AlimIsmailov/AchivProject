package com.ita.softserveinc.achiever.tool;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.entity.Role;

@Component
public class UserSearchRequestForm {
	private boolean firstNameColumn;
	private boolean lastNameColumn;
	private boolean emailColumn;
	private boolean loginColumn;
	private boolean dateColumn;
	private boolean advancedMode;
	private boolean adminRoleOption = true;
	private boolean studentRoleOption = true;
	private boolean managerRoleOption = true;
	private boolean userRoleOption = true;
	private String operationOnRoles = "AND";
	private String statusOption = "any";
	private String surName;
	private String searchTarget;
	private String firstNameOrLoginOrEmail;
	private Set<Role> selectedRoles;
	
	public boolean isValidRequest() {
		if ((firstNameOrLoginOrEmail != null) && (surName != null)) {
			if (!firstNameOrLoginOrEmail.equals("")) {
				return true;
			}
			if (!surName.equals("")) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isSearchByName() {
		return searchTarget.equals("searchByName") ? true : false;
	}
	
	public boolean isSearchByLogin() {
		return searchTarget.equals("searchByLogin") ? true : false;
	}
	
	public boolean isSearchByEmail() {
		return searchTarget.equals("searchByEmail") ? true : false;
	}
	
	public boolean isFirstNameColumn() {
		return firstNameColumn;
	}

	public void setFirstNameColumn(boolean firstNameColumn) {
		this.firstNameColumn = firstNameColumn;
	}

	public boolean isLastNameColumn() {
		return lastNameColumn;
	}

	public void setLastNameColumn(boolean lastNameColumn) {
		this.lastNameColumn = lastNameColumn;
	}

	public boolean isEmailColumn() {
		return emailColumn;
	}

	public void setEmailColumn(boolean emailColumn) {
		this.emailColumn = emailColumn;
	}

	public boolean isDateColumn() {
		return dateColumn;
	}

	public void setDateColumn(boolean dateColumn) {
		this.dateColumn = dateColumn;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getSearchTarget() {
		return searchTarget;
	}

	public void setSearchTarget(String searchTarget) {
		this.searchTarget = searchTarget;
	}
	
	public String getFirstNameOrLoginOrEmail() {
		return firstNameOrLoginOrEmail;
	}

	public void setFirstNameOrLoginOrEmail(String firstNameOrLoginOrEmail) {
		this.firstNameOrLoginOrEmail = firstNameOrLoginOrEmail;
	}

	public String getStatusOption() {
		return statusOption;
	}

	public void setStatusOption(String statusOption) {
		this.statusOption = statusOption;
	}

	public boolean isAdvancedMode() {
		return advancedMode;
	}

	public void setAdvancedMode(boolean advancedMode) {
		this.advancedMode = advancedMode;
	}

	public boolean isManagerRoleOption() {
		return managerRoleOption;
	}

	public void setManagerRoleOption(boolean managerRoleOption) {
		this.managerRoleOption = managerRoleOption;
	}

	public boolean isStudentRoleOption() {
		return studentRoleOption;
	}

	public void setStudentRoleOption(boolean studentRoleOption) {
		this.studentRoleOption = studentRoleOption;
	}

	public boolean isAdminRoleOption() {
		return adminRoleOption;
	}

	public void setAdminRoleOption(boolean adminRoleOption) {
		this.adminRoleOption = adminRoleOption;
	}

	public boolean isUserRoleOption() {
		return userRoleOption;
	}

	public void setUserRoleOption(boolean userRoleOption) {
		this.userRoleOption = userRoleOption;
	}

	public Set<Role> getSelectedRoles() {
		return selectedRoles;
	}

	public void setSelectedRoles(Set<Role> selectedRoles) {
		this.selectedRoles = selectedRoles;
	}

	public boolean isLoginColumn() {
		return loginColumn;
	}

	public void setLoginColumn(boolean loginColumn) {
		this.loginColumn = loginColumn;
	}

	public String getOperationOnRoles() {
		return operationOnRoles;
	}

	public void setOperationOnRoles(String operationOnRoles) {
		this.operationOnRoles = operationOnRoles;
	}

	@Override
	public String toString() {
		return "UserSearchRequestForm [firstNameColumn=" + firstNameColumn
				+ ", lastNameColumn=" + lastNameColumn + ", emailColumn="
				+ emailColumn + ", loginColumn=" + loginColumn
				+ ", dateColumn=" + dateColumn + ", advancedMode="
				+ advancedMode + ", adminRoleOption=" + adminRoleOption
				+ ", studentRoleOption=" + studentRoleOption
				+ ", managerRoleOption=" + managerRoleOption
				+ ", userRoleOption=" + userRoleOption + ", operationOnRoles="
				+ operationOnRoles + ", statusOption=" + statusOption
				+ ", surName=" + surName + ", searchTarget=" + searchTarget
				+ ", firstNameOrLoginOrEmail=" + firstNameOrLoginOrEmail
				+ ", selectedRoles=" + selectedRoles + "]";
	}
}
