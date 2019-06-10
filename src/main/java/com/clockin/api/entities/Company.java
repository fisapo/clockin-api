package com.clockin.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company implements Serializable {

	private static final long serialVersionUID = -13925928838439670L;

	private Long id;
	private String name;
	private String govId;
	private Date dateCreated;
	private Date dateUpdated;
	private List<Employee> employees;

	public Company() {
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "gov_id", nullable = false)
	public String getGovId() {
		return govId;
	}

	public void setGovId(String govId) {
		this.govId = govId;
	}

	@Column(name = "date_created", nullable = false)
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Column(name = "date_updated", nullable = false)
	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@PreUpdate
	public void preUpdate() {
		dateUpdated = new Date();
	}

	@PrePersist
	public void prePersist() {
		final Date now = new Date();
		dateCreated = now;
		dateUpdated = now;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", govId=" + govId + ", dateCreated=" + dateCreated
				+ ", dateUpdated=" + dateUpdated + "]";
	}
	
	
}
