package com.ita.softserveinc.achiever.tool;

import java.util.List;

import org.joda.time.DateTime;

import com.ita.softserveinc.achiever.entity.User;

public class GroupRatingInfoDto implements IDTO {

	private List<User> groupManager;
	private DateTime startDate;
	private DateTime endDate;

	public List<User> getGroupManager() {
		return groupManager;
	}
	public void setGroupManager(List<User> groupManager) {
		this.groupManager = groupManager;
	}
	public DateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}
	public DateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}
	@Override
	public int compareTo(IDTO o) {
		return 0;
	}

	@Override
	public String toString() {
		return "GroupRatingInfoDto [groupManager=" + groupManager
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
} 
