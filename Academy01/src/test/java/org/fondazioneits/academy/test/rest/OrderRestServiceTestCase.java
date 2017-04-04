package org.fondazioneits.academy.test.rest;

import javax.ws.rs.core.MediaType;

import org.fondazioneits.academy.feature.order.rest.InsertOrderRestServiceResponse;
import org.fondazioneits.academy.feature.order.service.InsertOrderServiceResponse;
import org.fondazioneits.academy.model.Customer;
import org.fondazioneits.academy.model.Order;
import org.fondazioneits.academy.model.OrderStatus;
import org.fondazioneits.academy.test.AcademyRestServiceTestCase;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.junit.Assert;
import org.junit.Test;

public class OrderRestServiceTestCase extends AcademyRestServiceTestCase {

	@SuppressWarnings("deprecation")
	@RunAsClient
	@Test
	public void insertNewOrderSuccessful() throws Exception {

		org.fondazioneits.academy.model.Customer customerModel = new Customer();
		customerModel.setId(new Long(1));

		org.fondazioneits.academy.model.Order orderModel = new Order();
		orderModel.setCode("A test code");
		orderModel.setCustomer(customerModel);

		ClientRequest request = new ClientRequest(this.deploymentUrl.toString() + RESOURCE_PREFIX + "/orders")
				.body(MediaType.APPLICATION_JSON, orderModel);

		ClientResponse<InsertOrderRestServiceResponse> clientResponse = request.post();

		int responseStatus = clientResponse.getStatus();

		Assert.assertEquals(200, responseStatus);

		InsertOrderRestServiceResponse responseEntity = clientResponse.getEntity(InsertOrderRestServiceResponse.class);

		InsertOrderServiceResponse businessResponse = responseEntity.getBusinessServiceResponse();
		Assert.assertNotNull(businessResponse);

		org.fondazioneits.academy.model.Order responseOrderModel = businessResponse.getOrder();
		Assert.assertNotNull(responseOrderModel);

		Assert.assertNotNull(responseOrderModel.getId());
		Assert.assertTrue(responseOrderModel.getOrderStatus() == OrderStatus.NEW);
		Assert.assertEquals("A test code", responseOrderModel.getCode());
		Assert.assertEquals(new Long(1), responseOrderModel.getCustomer().getId());
	}

}
