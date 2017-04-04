package org.fondazioneits.academy.feature.order.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.fondazioneits.academy.feature.customer.dao.CustomerDao;
import org.fondazioneits.academy.feature.order.dao.OrderDao;
import org.fondazioneits.academy.model.ErrorCode;
import org.fondazioneits.academy.model.OrderStatus;
import org.fondazioneits.academy.persistence.entity.Order;
import org.fondazioneits.academy.service.AcademyServiceException;

@Stateless
public class OrderBean implements OrderService {

	@Inject
	private CustomerDao customerDao;

	@Inject
	private OrderDao orderDao;

	@Override
	public InsertOrderServiceResponse insertNewOrder(InsertOrderServiceRequest request) throws AcademyServiceException {
		org.fondazioneits.academy.model.Order orderModel = request.getOrder();

		if (orderModel == null) {
			throw new AcademyServiceException(ErrorCode.MISSING_ORDER_PAYLOAD);
		}

		if (orderModel.getCustomer() == null) {
			throw new AcademyServiceException(ErrorCode.MISSING_ORDER_CUSTOMER_ID);
		}

		org.fondazioneits.academy.model.Customer customerModel = orderModel.getCustomer();
		if (customerModel.getId() == null) {
			throw new AcademyServiceException(ErrorCode.MISSING_ORDER_CUSTOMER_ID);
		}

		if (orderModel.getCode() == null || orderModel.getCode().trim().isEmpty()) {
			throw new AcademyServiceException(ErrorCode.MISSING_ORDER_CODE);
		}

		org.fondazioneits.academy.persistence.entity.Order orderEntity = new Order();

		Long customerId = orderModel.getCustomer().getId();

		org.fondazioneits.academy.persistence.entity.Customer customerEntity = this.customerDao.find(customerId);

		if (customerEntity == null) {
			throw new AcademyServiceException(ErrorCode.CUSTOMER_NOT_FOUND);
		}

		Date creationDate = GregorianCalendar.getInstance().getTime();

		orderEntity.setCode(orderModel.getCode());
		orderEntity.setCustomer(customerEntity);
		orderEntity.setLastModifiedDate(creationDate);
		orderEntity.setSubmissionDate(creationDate);
		orderEntity.setOrderStatus(OrderStatus.NEW);

		this.orderDao.save(orderEntity);

		orderModel.setId(orderEntity.getId());
		orderModel.setSubmissionDate(creationDate);
		orderModel.setOrderStatus(orderEntity.getOrderStatus());

		InsertOrderServiceResponse serviceResponse = new InsertOrderServiceResponse();
		serviceResponse.setOrder(orderModel);

		return serviceResponse;
	}

	@Override
	public RetrieveOrderListServiceResponse retrieveOrderList(RetrieveOrderListServiceRequest request)
			throws AcademyServiceException {

		RetrieveOrderListServiceResponse serviceResponse = new RetrieveOrderListServiceResponse();

		Long customerId = null;
		List<org.fondazioneits.academy.persistence.entity.Order> orderEntityList = null;
		if ((customerId = request.getCustomerId()) == null) {
			orderEntityList = this.orderDao.retrieveAllOrders();
		} else {
			org.fondazioneits.academy.persistence.entity.Customer customerEntity = this.customerDao.find(customerId);
			if (customerEntity == null) {
				throw new AcademyServiceException(ErrorCode.CUSTOMER_NOT_FOUND);
			}
			orderEntityList = customerEntity.getOrders();
		}

		Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();

		List<org.fondazioneits.academy.model.Order> orderModelList = new ArrayList<>();
		for (org.fondazioneits.academy.persistence.entity.Order currEntity : orderEntityList) {
			org.fondazioneits.academy.model.Order orderModel = mapper.map(currEntity,
					org.fondazioneits.academy.model.Order.class, "orders-no-customer");
			orderModelList.add(orderModel);
		}
		// mapper.map(orderEntityList, orderModelList, "orders-no-customer");

		serviceResponse.setOrderList(orderModelList);

		return serviceResponse;
	}

	@Override
	public ModifyOrderServiceResponse modifyOrder(ModifyOrderServiceRequest request) throws AcademyServiceException {

		org.fondazioneits.academy.model.Order order = request.getOrder();

		if (order == null) {
			throw new AcademyServiceException(ErrorCode.MISSING_ORDER_PAYLOAD);
		}

		Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();

		// questa entity NON é MANAGED!!!
		org.fondazioneits.academy.persistence.entity.Order orderEntity = mapper.map(order,
				org.fondazioneits.academy.persistence.entity.Order.class);

		this.orderDao.update(orderEntity);

		return new ModifyOrderServiceResponse();
	}

	@Override
	public OrderDetailsServiceResponse getOrderDetails(OrderDetailsServiceRequest request)
			throws AcademyServiceException {

		Long orderId = request.getOrderId();
		if (orderId == null) {
			throw new AcademyServiceException(ErrorCode.MISSING_ORDER_PAYLOAD);
		}

		org.fondazioneits.academy.persistence.entity.Order orderEntity = this.orderDao.find(orderId);
		if (orderEntity == null) {
			throw new AcademyServiceException(ErrorCode.ORDER_NOT_FOUND);
		}

		org.fondazioneits.academy.model.Order order = DozerBeanMapperSingletonWrapper.getInstance().map(orderEntity,
				org.fondazioneits.academy.model.Order.class);

		OrderDetailsServiceResponse serviceResponse = new OrderDetailsServiceResponse();
		serviceResponse.setOrder(order);

		return serviceResponse;
	}

}
