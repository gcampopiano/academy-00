package org.fondazioneits.academy.feature.order.service;

import org.fondazioneits.academy.service.AcademyServiceRequest;

public class RetrieveOrderListServiceRequest implements AcademyServiceRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 386643871893367937L;

	private Long customerId;

	public RetrieveOrderListServiceRequest() {
		super();
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

}
