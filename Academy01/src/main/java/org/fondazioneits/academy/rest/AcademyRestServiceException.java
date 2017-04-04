package org.fondazioneits.academy.rest;

import javax.ws.rs.core.Response.Status;

import org.fondazioneits.academy.model.ErrorCode;

public class AcademyRestServiceException extends Exception {

	private static final long serialVersionUID = 1665659158865804550L;

	private ErrorCode errorCode;

	private Status httpStatus;

	public AcademyRestServiceException(ErrorCode errorCode) {
		super();
		this.errorCode = errorCode;
		this.httpStatus = Status.BAD_REQUEST;
	}

	public AcademyRestServiceException(ErrorCode errorCode, Status httpStatus) {
		super();
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public Status getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(Status httpStatus) {
		this.httpStatus = httpStatus;
	}

}
