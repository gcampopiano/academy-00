package org.fondazioneits.academy.service;

import javax.ejb.ApplicationException;

import org.fondazioneits.academy.model.ErrorCode;

@ApplicationException(rollback = true)
public class AcademyServiceException extends Exception {

	private static final long serialVersionUID = -3509298153131466337L;

	private ErrorCode errorCode;

	public AcademyServiceException(ErrorCode errorCode) {
		super(errorCode.getErrorMessage());
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

}
