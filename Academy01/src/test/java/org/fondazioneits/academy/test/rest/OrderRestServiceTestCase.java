package org.fondazioneits.academy.test.rest;

import javax.ws.rs.core.MediaType;

import org.fondazioneits.academy.model.Customer;
import org.fondazioneits.academy.model.Order;
import org.fondazioneits.academy.rest.AcademyRestServiceException;
import org.fondazioneits.academy.test.AcademyRestServiceTestCase;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.junit.Test;

public class OrderRestServiceTestCase extends AcademyRestServiceTestCase {

	@SuppressWarnings("deprecation")
	@RunAsClient
	public void createOrderWithNoCodeThrowsException() throws Exception {

		org.fondazioneits.academy.model.Customer customerModel = new Customer();
		customerModel.setId(new Long(1));

		org.fondazioneits.academy.model.Order orderModel = new Order();
		orderModel.setCustomer(customerModel);

		ClientRequest request = new ClientRequest(this.deploymentUrl.toString() + RESOURCE_PREFIX + "/orders");
		request.body(MediaType.APPLICATION_JSON, orderModel);
		request.header("Accept", MediaType.APPLICATION_JSON);

		request.post();

	}

}
