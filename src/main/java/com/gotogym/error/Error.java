package com.gotogym.error;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Error {

	String errorId;

	String description;

	public Error() {
	}

	public Error(String errorId, String description) {
		super();
		this.errorId = errorId;
		this.description = description;
	}

	public String getErrorId() {
		return errorId;
	}

	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
