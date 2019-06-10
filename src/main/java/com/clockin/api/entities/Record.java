package com.clockin.api.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.clockin.api.enums.TypeEnum;

@Entity
@Table(name = "record")
public class Record implements Serializable{

	private static final long serialVersionUID = 7654351496078095573L;

	private Long id;
	private Date date;
	private String description;
	private String location;
	private Date dateCreated;
	private Date dateUpdated;
	private TypeEnum type;
	private Employee employee;

	public Record() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", nullable = false)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "description", nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "location", nullable = false)
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false)
	public TypeEnum getType() {
		return type;
	}

	public void setType(TypeEnum type) {
		this.type = type;
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

	@ManyToOne(fetch = FetchType.EAGER)
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
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
		return "Record [id=" + id + ", date=" + date + ", description=" + description + ", location=" + location
				+ ", dateCreated=" + dateCreated + ", dateUpdated=" + dateUpdated + ", type=" + type + ", employee="
				+ employee + "]";
	}

}
