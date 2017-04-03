package org.fondazioneits.academy.rest;

import javax.ws.rs.core.Response.Status;

public class AcademyRestServiceException extends Exception {

	private static final long serialVersionUID = 1665659158865804550L;

	private Status status;

	private String message;

	public AcademyRestServiceException(Status status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
