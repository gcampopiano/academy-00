package org.fondazioneits.academy.test.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.fondazioneits.academy.feature.customer.dao.CustomerDao;
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
import org.fondazioneits.academy.test.AcademyServiceTestCase;
import org.junit.Assert;
import org.junit.Test;

public class OrderServiceTestCase extends AcademyServiceTestCase {

	@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	private EntityManager em;

	@EJB
	private OrderService orderService;

	@Inject
	private OrderDao orderDao;

	@Inject
	private CustomerDao customerDao;

	@Resource
	private UserTransaction utx;

	@Test
	public void insertNewOrderSuccessful() throws Exception {

		org.fondazioneits.academy.persistence.entity.Customer customerEntity = new org.fondazioneits.academy.persistence.entity.Customer();
		customerEntity.setName("Guido" + Math.random());
		customerEntity.setSurname("Campopiano" + Math.random());
		customerEntity.setLastModifiedDate(new Date());

		this.utx.begin();
		this.em.joinTransaction();

		this.customerDao.save(customerEntity);
		Long customerId = customerEntity.getId();

		this.utx.commit();
		this.em.clear();

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
		Assert.assertNotNull(justInsertedOrderModel.getId());
		Assert.assertNotNull(justInsertedOrderModel.getCode());
		Assert.assertEquals("oneCode", justInsertedOrderModel.getCode());
		Assert.assertNotNull(justInsertedOrderModel.getOrderStatus());
		Assert.assertEquals(OrderStatus.NEW, justInsertedOrderModel.getOrderStatus());
		Assert.assertNotNull(justInsertedOrderModel.getSubmissionDate());

		this.em.clear();

		org.fondazioneits.academy.persistence.entity.Order justInsertedOrderEntity = this.em
				.find(org.fondazioneits.academy.persistence.entity.Order.class, justInsertedOrderModel.getId());

		Assert.assertNotNull(justInsertedOrderEntity);
		Assert.assertEquals("oneCode", justInsertedOrderEntity.getCode());
		Assert.assertTrue((justInsertedOrderEntity.getOrderStatus() != null)
				&& justInsertedOrderEntity.getOrderStatus() == OrderStatus.NEW);
		Assert.assertNotNull(justInsertedOrderEntity.getLastModifiedDate());

		List<org.fondazioneits.academy.persistence.entity.Order> orderList = this.orderDao
				.retrieveOrdersByCustomerId(customerId);

		Assert.assertTrue((orderList != null) && !orderList.isEmpty());

	}

	@Test(expected = AcademyServiceException.class)
	public void insertionWithNoOrderThrowsException() throws AcademyServiceException {
		InsertOrderServiceRequest serviceRequest = new InsertOrderServiceRequest();

		this.orderService.insertNewOrder(serviceRequest);
	}

	@Test(expected = AcademyServiceException.class)
	public void insertionWithEmptyOrderThrowsException() throws AcademyServiceException {
		// Order with any of mandatory parameters
		org.fondazioneits.academy.model.Order orderModel = new Order();

		InsertOrderServiceRequest serviceRequest = new InsertOrderServiceRequest();
		serviceRequest.setOrder(orderModel);

		this.orderService.insertNewOrder(serviceRequest);
	}

	@Test(expected = AcademyServiceException.class)
	public void insertionWithNoCustomerIdThrowsException() throws Exception {

		org.fondazioneits.academy.model.Order orderModel = new Order();
		orderModel.setCode("aCode");
		org.fondazioneits.academy.model.Customer customerModel = new Customer();
		orderModel.setCustomer(customerModel);

		InsertOrderServiceRequest serviceRequest = new InsertOrderServiceRequest();
		serviceRequest.setOrder(orderModel);

		this.orderService.insertNewOrder(serviceRequest);
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
	public void modifyOrderStatusSuccessful() throws Exception {

		org.fondazioneits.academy.persistence.entity.Customer c0 = new org.fondazioneits.academy.persistence.entity.Customer();
		c0.setName("Guido");
		c0.setSurname("Campopiano");
		c0.setLastModifiedDate(new Date());
		c0.setOrders(new ArrayList<>());

		Date submissionDate = new Date();
		org.fondazioneits.academy.persistence.entity.Order o0 = new org.fondazioneits.academy.persistence.entity.Order();
		o0.setCustomer(c0);
		o0.setCode("o0code");
		o0.setLastModifiedDate(submissionDate);
		o0.setOrderStatus(OrderStatus.NEW);
		o0.setSubmissionDate(submissionDate);

		c0.addOrder(o0);

		this.utx.begin();
		this.em.joinTransaction();

		this.em.persist(c0);
		this.em.persist(o0);

		this.utx.commit();
		this.em.clear();

		Long orderId = o0.getId();

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

		org.fondazioneits.academy.persistence.entity.Order orderEntity = this.em
				.find(org.fondazioneits.academy.persistence.entity.Order.class, orderId);
		Assert.assertEquals(OrderStatus.PENDING, orderEntity.getOrderStatus());

	}

}
