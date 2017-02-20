package org.fondazioneits.academy.test.service;

import javax.ejb.EJB;

import org.fondazioneits.academy.model.Customer;
import org.fondazioneits.academy.service.CreateCustomerServiceRequest;
import org.fondazioneits.academy.service.CreateCustomerServiceResponse;
import org.fondazioneits.academy.service.CustomerService;
import org.fondazioneits.academy.test.Academy01TestCase;
import org.junit.Assert;
import org.junit.Test;

public class CustomerServiceTestCase extends Academy01TestCase {

	@EJB
	private CustomerService customerService;

	@Test
	public void createCustomerSuccessful() {

		String name = "Oliver";
		String surname = "Medina";

		org.fondazioneits.academy.model.Customer customer = new Customer();
		customer.setName(name);
		customer.setSurname(surname);

		CreateCustomerServiceRequest serviceRequest = new CreateCustomerServiceRequest();
		serviceRequest.setCustomer(customer);

		CreateCustomerServiceResponse serviceResponse = this.customerService.createCustomer(serviceRequest);

		Assert.assertNotNull(serviceResponse);
		Assert.assertNotNull(serviceResponse.getCustomer());
		Assert.assertEquals(name, serviceResponse.getCustomer().getName());
		Assert.assertEquals(surname, serviceResponse.getCustomer().getSurname());
		Assert.assertNotNull(serviceResponse.getCustomer().getId());

	}

}
