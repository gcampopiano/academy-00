package org.fondazioneits.academy.feature.order.service;

import org.fondazioneits.academy.service.AcademyServiceResponse;

public class OrderDetailsServiceResponse implements AcademyServiceResponse {

	private static final long serialVersionUID = 3626442314699596922L;

	private org.fondazioneits.academy.model.Order order;

	public org.fondazioneits.academy.model.Order getOrder() {
		return order;
	}

	public void setOrder(org.fondazioneits.academy.model.Order order) {
		this.order = order;
	}

}
