package org.fondazioneits.academy.test.service;

import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fondazioneits.academy.feature.order.dao.OrderDao;
import org.fondazioneits.academy.feature.order.service.InsertOrderServiceRequest;
import org.fondazioneits.academy.feature.order.service.InsertOrderServiceResponse;
import org.fondazioneits.academy.feature.order.service.ModifyOrderServiceRequest;
import org.fondazioneits.academy.feature.order.service.ModifyOrderServiceResponse;
import org.fondazioneits.academy.feature.order.service.OrderDetailsServiceRequest;
import org.fondazioneits.academy.feature.order.service.OrderDetailsServiceResponse;
import org.fondazioneits.academy.feature.order.service.OrderService;
import org.fondazioneits.academy.feature.order.service.RetrieveOrderListServiceRequest;
import org.fondazioneits.academy.feature.order.service.RetrieveOrderListServiceResponse;
import org.fondazioneits.academy.model.Customer;
import org.fondazioneits.academy.model.Order;
import org.fondazioneits.academy.model.OrderStatus;
import org.fondazioneits.academy.service.AcademyServiceException;
import org.fondazioneits.academy.test.Academy01TestCase;
import org.junit.Assert;
import org.junit.Test;

public class OrderServiceTestCase extends Academy01TestCase {

	@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	private EntityManager em;

	@EJB
	private OrderService orderService;

	@Inject
	private OrderDao orderDao;

	@Test
	public void insertNewOrderSuccessful() {

		Long customerId = new Long(1);

		org.fondazioneits.academy.model.Customer customerModel = new Customer();
		customerModel.setId(customerId);

		org.fondazioneits.academy.model.Order orderModel = new Order();
		orderModel.setCustomer(customerModel);
		orderModel.setCode("oneCode");

		InsertOrderServiceRequest serviceRequest = new InsertOrderServiceRequest();
		serviceRequest.setOrder(orderModel);

		InsertOrderServiceResponse serviceResponse = null;

		try {
			serviceResponse = this.orderService.insertNewOrder(serviceRequest);
		} catch (AcademyServiceException e) {
			Assert.fail();
		}

		Assert.assertNotNull(serviceResponse);

		org.fondazioneits.academy.model.Order justInsertedOrderModel = serviceResponse.getOrder();
		Assert.assertNotNull(justInsertedOrderModel);

		this.em.clear();

		org.fondazioneits.academy.persistence.entity.Order justInsertedOrderEntity = this.em
				.find(org.fondazioneits.academy.persistence.entity.Order.class, justInsertedOrderModel.getId());

		Assert.assertNotNull(justInsertedOrderEntity);
		Assert.assertEquals("oneCode", justInsertedOrderEntity.getCode());

		List<org.fondazioneits.academy.persistence.entity.Order> orderList = this.orderDao
				.retrieveOrdersByCustomerId(customerId);

		Assert.assertTrue((orderList != null) && !orderList.isEmpty());

	}

	@Test(expected = AcademyServiceException.class)
	public void retrieveOrderListThrowsExceptionWithoutCustomerId() throws AcademyServiceException {

		RetrieveOrderListServiceRequest serviceRequest = new RetrieveOrderListServiceRequest();
		serviceRequest.setCustomerId(null);

		this.orderService.retrieveOrderList(serviceRequest);
	}

	@Test
	public void retrieveOrderListSuccessful() {
		Long customerId = new Long(1);

		RetrieveOrderListServiceRequest serviceRequest = new RetrieveOrderListServiceRequest();
		serviceRequest.setCustomerId(customerId);

		RetrieveOrderListServiceResponse serviceResponse = null;
		try {
			serviceResponse = this.orderService.retrieveOrderList(serviceRequest);
		} catch (AcademyServiceException e) {
			Assert.fail();
		}

		Assert.assertNotNull(serviceResponse);

		List<org.fondazioneits.academy.model.Order> orderList = serviceResponse.getOrderList();
		Assert.assertNotNull(orderList);
		Assert.assertFalse(orderList.isEmpty());

	}

	@Test(expected = AcademyServiceException.class)
	public void modifyWithNoOrderThrowsException() throws AcademyServiceException {

		ModifyOrderServiceRequest serviceRequest = new ModifyOrderServiceRequest();

		this.orderService.modifyOrder(serviceRequest);

	}

	@Test
	public void modifyOrderSuccessful() {

		Long orderId = new Long(11);

		OrderDetailsServiceRequest orderDetailsRequest = new OrderDetailsServiceRequest();
		orderDetailsRequest.setOrderId(orderId);

		OrderDetailsServiceResponse orderDetailsResponse = null;
		try {
			orderDetailsResponse = this.orderService.getOrderDetails(orderDetailsRequest);
		} catch (AcademyServiceException e1) {
			Assert.fail();
		}

		Assert.assertNotNull(orderDetailsResponse);
		Assert.assertNotNull(orderDetailsResponse.getOrder());

		org.fondazioneits.academy.model.Order order = orderDetailsResponse.getOrder();
		order.setOrderStatus(OrderStatus.PENDING);

		ModifyOrderServiceRequest serviceRequest = new ModifyOrderServiceRequest();
		serviceRequest.setOrder(order);

		ModifyOrderServiceResponse serviceResponse = null;

		try {
			serviceResponse = this.orderService.modifyOrder(serviceRequest);
		} catch (AcademyServiceException e) {
			Assert.fail();
		}

		Assert.assertNotNull(serviceResponse);

		this.em.clear();

		org.fondazioneits.academy.persistence.entity.Order orderEntity = this.em
				.find(org.fondazioneits.academy.persistence.entity.Order.class, orderId);
		Assert.assertEquals(OrderStatus.PENDING, orderEntity.getOrderStatus());

	}

}
