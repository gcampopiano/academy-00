package org.fondazioneits.academy.feature.order.service;

import org.fondazioneits.academy.model.Order;
import org.fondazioneits.academy.service.AcademyServiceRequest;

public class InsertOrderServiceRequest implements AcademyServiceRequest {

	private static final long serialVersionUID = -8070783823355791671L;

	private Order order;

	public InsertOrderServiceRequest() {
		super();
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
