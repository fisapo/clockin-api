package com.clockin.api.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.clockin.api.enums.ProfileEnum;

@Entity
@Table
public class Employee implements Serializable {

	private static final long serialVersionUID = 6384047432719809232L;

	private Long id;
	private String name;
	private String email;
	private String password;
	private String governmentId;
	private BigDecimal hourRate;
	private Float numWorkedHoursDay;
	private Float numBreakHoursDay;
	private ProfileEnum profile;
	private Date dateCreated;
	private Date dateUpdated;
	private Company company;
	private List<Record> records;

	public Employee() {
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

	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "government_id", nullable = false)
	public String getGovernmentId() {
		return governmentId;
	}

	public void setGovernmentId(String governmentId) {
		this.governmentId = governmentId;
	}

	@Column(name = "hour_rate", nullable = false)
	public BigDecimal getHourRate() {
		return hourRate;
	}

	@Transient
	public Optional<BigDecimal> getHourRateOpt(){
		return Optional.ofNullable(hourRate);
	}

	public void setHourRate(BigDecimal hourRate) {
		this.hourRate = hourRate;
	}

	@Column(name = "num_worked_hours_day", nullable = false)
	public Float getNumWorkedHoursDay() {
		return numWorkedHoursDay;
	}

	@Transient
	public Optional<Float> getNumWorkedHoursDayOpt(){
		return Optional.ofNullable(numWorkedHoursDay);
	}

	public void setNumWorkedHoursDay(Float numWorkedHoursDay) {
		this.numWorkedHoursDay = numWorkedHoursDay;
	}

	@Column(name = "num_break_hours_day", nullable = false)
	public Float getNumBreakHoursDay() {
		return numBreakHoursDay;
	}

	@Transient
	public Optional<Float> getNumBreakHoursDayOpt(){
		return Optional.ofNullable(numBreakHoursDay);
	}

	public void setNumBreakHoursDay(Float numBreakHoursDay) {
		this.numBreakHoursDay = numBreakHoursDay;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "profile", nullable = false)
	public ProfileEnum getProfile() {
		return profile;
	}

	public void setProfile(ProfileEnum profile) {
		this.profile = profile;
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
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
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
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", governmentId=" + governmentId + ", hourRate=" + hourRate + ", numWorkedHoursDay="
				+ numWorkedHoursDay + ", numBreakHoursDay=" + numBreakHoursDay + ", profile=" + profile
				+ ", dateCreated=" + dateCreated + ", dateUpdated=" + dateUpdated + ", company=" + company + "]";
	}

}
