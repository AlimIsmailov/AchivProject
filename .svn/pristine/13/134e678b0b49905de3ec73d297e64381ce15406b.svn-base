package com.ita.softserveinc.achiever.tool;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Component;

import com.ita.softserveinc.achiever.entity.User;

@Component
public class UserListForm {
	
	private int elementsPerPage = 5;

	private int listSize;
	private List<User> userList;
	private PagedListHolder<User> pageHolder;
	private List<User> pageList;
	

	public int getElementsPerPage() {
		return elementsPerPage;
	}

	public void setElementsPerPage(int elementsPerPage) {
		this.elementsPerPage = elementsPerPage;
	}
	
	public void nextPage() {
		if (pageHolder != null && (!pageHolder.isLastPage()))
		pageHolder.nextPage();
	}
	
	@Override
	public String toString() {
		return "UserListForm [listSize=" + listSize + ", userList=" + userList
				+ ", pageHolder=" + pageHolder + ", pageList=" + pageList + "]";
	}

	public void previousPage() {
		if (pageHolder != null && (!pageHolder.isFirstPage()))
		pageHolder.previousPage();
	}
	
	public void setUpPagination () {
		pageHolder = new PagedListHolder<User>();
		pageHolder.setSource(this.userList);
		pageHolder.setPageSize(elementsPerPage);
	}

	
	public List<User> getUserList() {
		return userList;
	}
	
	public List<User> getPageList() {
		List<User> source;
		
		if (pageHolder != null) {
			source = pageHolder.getPageList();
			pageList = new ArrayList<User>(source);
		}
		return pageList;
	}
	
	public void setPageList(List<User> pageList) {
		this.pageList = pageList;
	}
	
	public List<User> getModifiedPageContent() {
		return this.pageList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
		setUpPagination();
	}

	public int getListSize() {
		if (userList != null) {
			listSize = userList.size();
		} else
			listSize = 0;
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}
	
	public int getPage() {
		return pageHolder.getPage() + 1;
	}
	
	public void setPage(int page) {
		pageHolder.setPage(page);	
	}
	
	public int getTotalPagesCount() {
		return pageHolder.getPageCount();
	}
}
