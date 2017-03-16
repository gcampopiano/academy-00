package org.fondazioneits.academy.feature.order.service;

import org.fondazioneits.academy.service.AcademyServiceRequest;

public class OrderDetailsServiceRequest implements AcademyServiceRequest {

	private static final long serialVersionUID = 1913263528236813320L;

	private Long orderId;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

}
