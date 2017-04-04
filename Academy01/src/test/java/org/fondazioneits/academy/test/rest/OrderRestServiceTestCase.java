package org.fondazioneits.academy.test.rest;

import javax.ws.rs.core.MediaType;

import org.fondazioneits.academy.feature.order.rest.InsertOrderRestServiceResponse;
import org.fondazioneits.academy.feature.order.rest.OrderDetailsRestServiceResponse;
import org.fondazioneits.academy.feature.order.service.InsertOrderServiceResponse;
import org.fondazioneits.academy.feature.order.service.OrderDetailsServiceResponse;
import org.fondazioneits.academy.model.Customer;
import org.fondazioneits.academy.model.ErrorCode;
import org.fondazioneits.academy.model.Order;
import org.fondazioneits.academy.model.OrderStatus;
import org.fondazioneits.academy.test.AcademyRestServiceTestCase;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.junit.Assert;
import org.junit.Test;

/**
 * !!! TO DO: inserire nuove entity prima di ogni test
 * 
 * @author guido.campopiano
 *
 */

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

		ClientRequest request = new ClientRequest(this.uriTemplate()).body(MediaType.APPLICATION_JSON, orderModel);

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

	@SuppressWarnings("deprecation")
	@RunAsClient
	@Test
	public void insertNewOrderWithoutCodeReturns400() throws Exception {
		org.fondazioneits.academy.model.Customer customerModel = new Customer();
		customerModel.setId(new Long(1));

		// esplicitamente non imposto il codice dell'ordine
		org.fondazioneits.academy.model.Order orderModel = new Order();
		orderModel.setCustomer(customerModel);

		ClientRequest request = new ClientRequest(this.uriTemplate()).body(MediaType.APPLICATION_JSON, orderModel);

		ClientResponse<ErrorCode> clientResponse = request.post();

		int responseStatus = clientResponse.getStatus();
		Assert.assertEquals(400, responseStatus);
		Assert.assertEquals(ErrorCode.MISSING_ORDER_CODE, clientResponse.getEntity(ErrorCode.class));
	}

	@SuppressWarnings("deprecation")
	@RunAsClient
	@Test
	public void insertNewOrderWithoutCustomerIdReturns400() throws Exception {

		// esplicitamente non imposto l'id del customer
		org.fondazioneits.academy.model.Customer customerModel = new Customer();

		org.fondazioneits.academy.model.Order orderModel = new Order();
		orderModel.setCode("My test code");
		orderModel.setCustomer(customerModel);

		ClientRequest request = new ClientRequest(this.uriTemplate()).body(MediaType.APPLICATION_JSON, orderModel);

		ClientResponse<ErrorCode> clientResponse = request.post();

		int responseStatus = clientResponse.getStatus();
		Assert.assertEquals(400, responseStatus);
		Assert.assertEquals(ErrorCode.MISSING_ORDER_CUSTOMER_ID, clientResponse.getEntity(ErrorCode.class));
	}

	@SuppressWarnings("deprecation")
	@RunAsClient
	@Test
	public void retrieveOrderDetailsSuccessful() throws Exception {

		ClientRequest request = new ClientRequest(this.uriTemplate() + "/{orderId}").pathParameter("orderId",
				new Long(11));

		ClientResponse<OrderDetailsRestServiceResponse> clientResponse = request.get();
		int returnStatus = clientResponse.getStatus();
		Assert.assertEquals(200, returnStatus);

		OrderDetailsRestServiceResponse restServiceResponse = clientResponse
				.getEntity(OrderDetailsRestServiceResponse.class);
		Assert.assertNotNull(restServiceResponse);

		OrderDetailsServiceResponse businessServiceResponse = restServiceResponse.getBusinessServiceResponse();
		Assert.assertNotNull(businessServiceResponse);

		org.fondazioneits.academy.model.Order orderModel = businessServiceResponse.getOrder();
		Assert.assertNotNull(orderModel);
	}

	@Override
	public String uriTemplate() {
		return this.deploymentUrl.toString() + RESOURCE_PREFIX + "/orders";
	}

}
