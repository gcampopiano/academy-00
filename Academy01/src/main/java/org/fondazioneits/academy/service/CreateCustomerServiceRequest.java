package org.fondazioneits.academy.service;

public class CreateCustomerServiceRequest implements AcademyServiceRequest {

	private static final long serialVersionUID = 6192676607134174324L;

	private org.fondazioneits.academy.model.Customer customer;

	public org.fondazioneits.academy.model.Customer getCustomer() {
		return customer;
	}

	public void setCustomer(org.fondazioneits.academy.model.Customer customer) {
		this.customer = customer;
	}

}
