package org.fondazioneits.academy.service;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class AcademyServiceException extends Exception {

	private static final long serialVersionUID = -3509298153131466337L;

	public AcademyServiceException(String arg0) {
		super(arg0);
	}

}
