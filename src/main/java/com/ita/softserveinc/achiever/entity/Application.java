package com.ita.softserveinc.achiever.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.common.base.Objects;

/**
 * 
 * @author Andriana
 *
 */
@NamedQueries({
	@NamedQuery(name = "Application.findByUser", query="SELECT a FROM Application a WHERE a.user=:user"),
	@NamedQuery(name="Application.findActiveByGroup", query="SELECT a FROM Application a WHERE a.group=:group AND a.status=:status"),
	@NamedQuery(name="Application.findByStatus", query="SELECT a FROM Application a WHERE a.status=:status")
})
@Entity
@Table(name = "APPLICATIONS")
public class Application extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8213873526705936212L;
	
	@Column(name="STATUS")
	private int status;

	@ManyToOne
	@JoinColumn(name = "GROUP_ID")
	private Group group;

	@OneToOne
	@JoinColumn(name="USER_ID")
	private User user;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Group getGroup() {
		return group;
	}
	
	public void setGroup(Group group) {
		this.group = group;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this.group, this.user);
	}

	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Application that = (Application) obj;
		return Objects.equal(this.group, that.group)
				&& Objects.equal(this.user, that.user);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("group", this.group.getGroupName())
				.add("user", this.user.getLogin()).add("status", this.status).toString();
	}


}
