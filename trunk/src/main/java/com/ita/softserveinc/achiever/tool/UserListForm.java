package com.ita.softserveinc.achiever.tool;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.entity.User;

@Component
public class UserListForm {
	 private List<User> userList;
	 private List<String> deleteUserIdList;

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<String> getDeleteUserIdList() {
		return deleteUserIdList;
	}

	public void setDeleteUserIdList(List<String> deleteUserIdList) {
		this.deleteUserIdList = deleteUserIdList;
	}
}
