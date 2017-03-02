package org.fondazioneits.academy.feature.order.service;

import org.fondazioneits.academy.model.Order;
import org.fondazioneits.academy.service.AcademyServiceResponse;

public class InsertOrderServiceResponse implements AcademyServiceResponse {

	private static final long serialVersionUID = -6531549125045958244L;

	private Order order;

	public InsertOrderServiceResponse() {
		super();
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
