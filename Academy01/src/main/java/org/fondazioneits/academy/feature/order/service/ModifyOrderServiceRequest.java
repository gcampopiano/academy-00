package org.fondazioneits.academy.feature.order.service;

import org.fondazioneits.academy.service.AcademyServiceRequest;

public class ModifyOrderServiceRequest implements AcademyServiceRequest {

	private static final long serialVersionUID = 3867362903796846235L;

	private org.fondazioneits.academy.model.Order order;

	public org.fondazioneits.academy.model.Order getOrder() {
		return order;
	}

	public void setOrder(org.fondazioneits.academy.model.Order order) {
		this.order = order;
	}

}
