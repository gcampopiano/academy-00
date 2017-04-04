package org.fondazioneits.academy.rest;

import java.io.Serializable;

import javax.ws.rs.core.Response.Status;

public class AcademyRestExceptionValue implements Serializable {

	private static final long serialVersionUID = -419026413465229599L;

	private Status status;

	private String message;

	private String code;

	public AcademyRestExceptionValue(Status status, String message, String code) {
		super();
		this.status = status;
		this.message = message;
		this.code = code;
	}

}
