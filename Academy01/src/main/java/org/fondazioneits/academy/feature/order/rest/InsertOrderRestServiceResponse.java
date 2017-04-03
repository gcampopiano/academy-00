package org.fondazioneits.academy.feature.order.rest;

import org.fondazioneits.academy.feature.order.service.InsertOrderServiceResponse;
import org.fondazioneits.academy.rest.AcademyRestServiceResponse;

public class InsertOrderRestServiceResponse extends AcademyRestServiceResponse<InsertOrderServiceResponse> {

	private static final long serialVersionUID = 8860699704261190928L;

	public InsertOrderRestServiceResponse(InsertOrderServiceResponse businessServiceResponse) {
		super(businessServiceResponse);

	}

}
