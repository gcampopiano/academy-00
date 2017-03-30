package org.fondazioneits.academy.feature.order.rest;

import org.fondazioneits.academy.feature.order.service.RetrieveOrderListServiceResponse;
import org.fondazioneits.academy.rest.AcademyRestServiceResponse;

public class RetrieveOrderListRestServiceResponse extends AcademyRestServiceResponse<RetrieveOrderListServiceResponse> {

	private static final long serialVersionUID = -3848522796871660083L;

	public RetrieveOrderListRestServiceResponse(RetrieveOrderListServiceResponse businessServiceResponse) {
		super(businessServiceResponse);
	}

}
