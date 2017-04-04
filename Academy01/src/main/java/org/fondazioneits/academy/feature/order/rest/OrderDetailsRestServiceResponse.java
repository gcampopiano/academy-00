package org.fondazioneits.academy.feature.order.rest;

import org.fondazioneits.academy.feature.order.service.OrderDetailsServiceResponse;
import org.fondazioneits.academy.rest.AcademyRestServiceResponse;

public class OrderDetailsRestServiceResponse extends AcademyRestServiceResponse<OrderDetailsServiceResponse> {

	private static final long serialVersionUID = -1393825749075208699L;

	public OrderDetailsRestServiceResponse() {
		super();
	}

	public OrderDetailsRestServiceResponse(OrderDetailsServiceResponse serviceResponse) {
		super(serviceResponse);
	}

}
