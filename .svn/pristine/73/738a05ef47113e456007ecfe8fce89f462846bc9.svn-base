package com.ita.softserveinc.achiever.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -5268130166874652925L;

	// @Version Long version;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	/*@TableGenerator(name = "IdGenerator", pkColumnValue = "BaseEntityId", table = "Sequence_Table", allocationSize = 1)*/
	
	@Column(name = "ID")
	private Long id;

	@Column(name = "CREATED", insertable = false, nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@DateTimeFormat(pattern="dddd, dd MMMM yyyy	hh:mm tt")
	private Date created;
	
	/* 
	 * @Column(name = "LAST_UPDATE", columnDefinition =
	 * "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false, insertable =
	 * false) private Timestamp lastUpdate;
	 */

	public BaseEntity() {
		/* lastUpdate = new Timestamp(System.currentTimeMillis()); */
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	public void setId(Long id){
		this.id=id;
	}
	
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
