package com.gotogym.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "gym_client")
public class GymClient {

	@Column(name = "client_name")
	String clientName;

	@Id
	@Column(name = "client_mobile")
	Long phone;

	@Column(name = "client_mailid")
	String email;

	@Column(name = "client_hash")
	byte[] hash;

	@Column(name = "client_pass")
	byte[] salt;

	@Column(name = "client_reg_date")
	Date registerDate;

	@Column(name = "client_update_time")
	Date updatedTime;

	@Column(name = "client_address1")
	String address1;

	@Column(name = "client_address2")
	String address2;

	@Column(name = "client_city")
	String city;

	@Transient
	String password;

	public GymClient() {
	}

	public GymClient(String clientName, Long phone, String email, byte[] hash, byte[] salt, Date registerDate,
			Date updatedTime, String address1, String address2, String city, String password) {
		super();
		this.clientName = clientName;
		this.phone = phone;
		this.email = email;
		this.hash = hash;
		this.salt = salt;
		this.registerDate = registerDate;
		this.updatedTime = updatedTime;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.password = password;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getHash() {
		return hash;
	}

	public void setHash(byte[] hash) {
		this.hash = hash;
	}

	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
