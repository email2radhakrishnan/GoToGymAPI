package com.gotogym.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "plans")
public class Plan {

	@Id
	@Column(name = "plan_name")
	String planName;

	@Column(name = "total_amount")
	Integer totalAmount;

	@Column(name = "validity")
	Integer validityInDays;

	@Column(name = "number_of_entries")
	Integer numberOfEntries;

	public Plan() {
	}

	public Plan(String planName, Integer totalAmount, Integer validityInDays, Integer numberOfEntries) {
		super();
		this.planName = planName;
		this.totalAmount = totalAmount;
		this.validityInDays = validityInDays;
		this.numberOfEntries = numberOfEntries;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getValidityInDays() {
		return validityInDays;
	}

	public void setValidityInDays(Integer validityInDays) {
		this.validityInDays = validityInDays;
	}

	public Integer getNumberOfEntries() {
		return numberOfEntries;
	}

	public void setNumberOfEntries(Integer numberOfEntries) {
		this.numberOfEntries = numberOfEntries;
	}

}
