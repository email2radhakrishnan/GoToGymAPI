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
@Table(name = "gym_logs")
public class GymLogs {

	@Id
	@GeneratedValue
	@Column(name = "id")
	Integer id;

	@Column(name = "client_name")
	String clientName;

	@Column(name = "user_name")
	String userName;

	@Column(name = "plan_name")
	String planName;

	@Column(name = "date_visited")
	Date dateVisited;

	@Column(name = "paid")
	Boolean isPaid;

	public GymLogs() {
	}

	public GymLogs(Integer id, String clientName, String userName, String planName, Date dateVisited, Boolean isPaid) {
		super();
		this.id = id;
		this.clientName = clientName;
		this.userName = userName;
		this.planName = planName;
		this.dateVisited = dateVisited;
		this.isPaid = isPaid;
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

	public Date getDateVisited() {
		return dateVisited;
	}

	public void setDateVisited(Date dateVisited) {
		this.dateVisited = dateVisited;
	}

	public Boolean getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}

}
