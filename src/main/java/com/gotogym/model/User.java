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
@Table(name = "user_info")
public class User {

	@Column(name = "mailid")
	String email;
	@Id
	@Column(name = "mobile")
	Long phone;
	@Column(name = "first_name")
	String firstName;
	@Column(name = "last_name")
	String lastName;
	@Column(name = "city")
	String city;
	@Column(name = "validSubscription")
	@Type(type="yes_no")
	Boolean validSubscription;
	@Column(name = "createdDate")
	Date createdDate;
	@Column(name = "updatedDate")
	Date updatedDate;

	public User() {
	}

	public User(String email, Long phone, String firstName, String lastName, String city, Boolean validSubscription,
			Date createdDate, Date updatedDate) {
		super();
		this.email = email;
		this.phone = phone;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.validSubscription = validSubscription;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Boolean getValidSubscription() {
		return validSubscription;
	}

	public void setValidSubscription(Boolean validSubscription) {
		this.validSubscription = validSubscription;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}
