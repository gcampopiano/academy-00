package org.fondazioneits.academy.rest;

import java.io.Serializable;

import org.fondazioneits.academy.service.AcademyServiceResponse;

public abstract class AcademyRestServiceResponse<BSR extends AcademyServiceResponse> implements Serializable {

	private static final long serialVersionUID = 1660284450350670333L;

	private BSR businessServiceResponse;

	public AcademyRestServiceResponse(BSR businessServiceResponse) {
		super();
		this.businessServiceResponse = businessServiceResponse;
	}

	public BSR getBusinessServiceResponse() {
		return businessServiceResponse;
	}

	public void setBusinessServiceResponse(BSR businessServiceResponse) {
		this.businessServiceResponse = businessServiceResponse;
	}

}
