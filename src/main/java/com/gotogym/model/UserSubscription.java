package com.gotogym.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Type;

@XmlRootElement
@Entity
@Table(name = "user_subscription")
public class UserSubscription {

	@Id
	@Column(name = "user_name")
	String userName;

	@Column(name = "plan_name")
	String planName;

	@Column(name = "number_of_entries")
	Integer numberOfEntries;

	@Column(name = "used_entries")
	Integer usedEntries;

	@Column(name = "purchased_date")
	Date purchasedDate;

	@Column(name = "expiry_date")
	Date expiryDate;

	@Column(name = "active")
	@Type(type="yes_no")
	Boolean isActive;

	public UserSubscription() {
	}

	public UserSubscription(String userName, String planName, Integer numberOfEntries, Integer usedEntries,
			Date purchasedDate, Date expiryDate, Boolean isActive) {
		super();
		this.userName = userName;
		this.planName = planName;
		this.numberOfEntries = numberOfEntries;
		this.usedEntries = usedEntries;
		this.purchasedDate = purchasedDate;
		this.expiryDate = expiryDate;
		this.isActive = isActive;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Integer getNumberOfEntries() {
		return numberOfEntries;
	}

	public void setNumberOfEntries(Integer numberOfEntries) {
		this.numberOfEntries = numberOfEntries;
	}

	public Integer getUsedEntries() {
		return usedEntries;
	}

	public void setUsedEntries(Integer usedEntries) {
		this.usedEntries = usedEntries;
	}

	public Date getPurchasedDate() {
		return purchasedDate;
	}

	public void setPurchasedDate(Date purchasedDate) {
		this.purchasedDate = purchasedDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
