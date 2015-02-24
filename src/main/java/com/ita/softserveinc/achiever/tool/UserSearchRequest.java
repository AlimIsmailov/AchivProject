package com.ita.softserveinc.achiever.tool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserSearchRequest {

	private boolean firstNameColumn;
	private boolean lastNameColumn;
	private boolean emailColumn;
	private boolean dateColumn;

	private String firstNameField;
	private String lastNameField;
	private String loginField;
	
	private List<String> selectedRoles;
	
	public boolean isFieldsInRequest() {
		if (firstNameField != "")
			return true;
		if (lastNameField != "")
			return true;
		if (loginField != "")
			return true;
		return false;
	}
	
	public boolean isValidRequest() {
		if (isRoles() || isFieldsInRequest()) 
			return true;
		return false;
	}
	
	public void setAllRolesSelected() {
		selectedRoles = new ArrayList<String>();
		selectedRoles.add("ALL");
	}
	
	public boolean isAllRolesSected() {

		if (isRoles()) {
			for (String role : selectedRoles) {
				if (role.equals("ALL"))
					return true;
			}
		}
		return false;
	}

	public boolean isRoles() {
		if (selectedRoles == null)
			return false;
		else
			return true;
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

	public String getFirstNameField() {
		return firstNameField;
	}

	public void setFirstNameField(String firstNameField) {
		this.firstNameField = firstNameField;
	}

	public String getLastNameField() {
		return lastNameField;
	}

	public void setLastNameField(String lastNameField) {
		this.lastNameField = lastNameField;
	}

	public String getLoginField() {
		return loginField;
	}

	public void setLoginField(String loginField) {
		this.loginField = loginField;
	}

	public List<String> getSelectedRoles() {
		return selectedRoles;
	}

	public void setSelectedRoles(List<String> selectedRoles) {
		this.selectedRoles = selectedRoles;
	}

	@Override
	public String toString() {
		return "firstNameField: " + this.firstNameField + "\n"
				+ "lastNameField: " + this.lastNameField + "\n"
						+ "loginField: " + this.loginField + "\n"
						+ printRoles(selectedRoles);
	}
	
	private String printRoles(List<String> roleList) {
		StringBuilder sBuilder = new StringBuilder();
		
		if (roleList == null)
			return null;
		
		for(String role: roleList) {
			sBuilder.append(role);
			sBuilder.append("\n");
		}
		return sBuilder.toString();
	}
}
