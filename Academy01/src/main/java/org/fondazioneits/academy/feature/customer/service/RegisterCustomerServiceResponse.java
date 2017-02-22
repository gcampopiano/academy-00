package org.fondazioneits.academy.feature.customer.service;

import org.fondazioneits.academy.service.AcademyServiceResponse;

public class RegisterCustomerServiceResponse implements AcademyServiceResponse {

	private static final long serialVersionUID = 1382257041515014936L;

	private org.fondazioneits.academy.model.Customer customer;

	public org.fondazioneits.academy.model.Customer getCustomer() {
		return customer;
	}

	public void setCustomer(org.fondazioneits.academy.model.Customer customer) {
		this.customer = customer;
	}

}
