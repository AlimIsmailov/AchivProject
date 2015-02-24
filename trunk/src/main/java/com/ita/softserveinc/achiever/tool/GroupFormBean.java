package com.ita.softserveinc.achiever.tool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Objects;
import com.ita.softserveinc.achiever.controller.GroupController;
import com.ita.softserveinc.achiever.entity.Group;
import com.ita.softserveinc.achiever.entity.User;
import com.ita.softserveinc.achiever.service.IUserService;
@Component
public class GroupFormBean {
	
	private static final Logger logger = LoggerFactory
			.getLogger(GroupFormBean.class);

	private Group group;
	private String start;
	private String end;
	private String[] users;
	private boolean isFuture;
	private boolean isCurrent;
	private boolean isFinished;
	/*@Autowired
	private IUserService userService;*/
	
	public GroupFormBean() {
	}
	
	public GroupFormBean(Group group) {
		super();
		this.group = group;
	}
	
	
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String[] getUsers() {
		return users;
	}
	public void setUsers(String[] users) {
		this.users = users;
	}	
	
	public boolean getIsFuture() {
		return isFuture;
	}

	public void setIsFuture(boolean isFuture) {
		this.isFuture = isFuture;
	}

	public boolean getIsCurrent() {
		return isCurrent;
	}

	public void setIsCurrent(boolean isCurrent) {
		this.isCurrent = isCurrent;
	}

	public boolean getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.group);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof GroupFormBean)) {
			return false;
		}
		final GroupFormBean other = (GroupFormBean) obj;
		return Objects.equal(this.group, other.group);
	}

	@Override
	public String toString() {

		return Objects.toStringHelper(this).add("group", this.group)
				.add("users", Arrays.deepToString(users))
				.toString();

	}
}
