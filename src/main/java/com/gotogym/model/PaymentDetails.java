package com.gotogym.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "payment_details")
public class PaymentDetails {

	@Id
	@GeneratedValue
	@Column(name = "id")
	Integer id;

	@Column(name = "client_name")
	String clientName;

	@Column(name = "number_of_users")
	Integer numberOfUsers;

	@Column(name = "total_amount")
	Integer totalAmount;

	@Column(name = "paid_date")
	Date paidDate;

	public PaymentDetails() {
	}

	public PaymentDetails(Integer id, String clientName, Integer numberOfUsers, Integer totalAmount, Date paidDate) {
		super();
		this.id = id;
		this.clientName = clientName;
		this.numberOfUsers = numberOfUsers;
		this.totalAmount = totalAmount;
		this.paidDate = paidDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Integer getNumberOfUsers() {
		return numberOfUsers;
	}

	public void setNumberOfUsers(Integer numberOfUsers) {
		this.numberOfUsers = numberOfUsers;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

}
