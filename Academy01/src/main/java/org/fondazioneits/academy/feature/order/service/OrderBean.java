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

		org.fondazioneits.academy.persistence.entity.Order orderEntity = new Order();

		Long customerId = orderModel.getCustomer().getId();

		org.fondazioneits.academy.persistence.entity.Customer customerEntity = this.customerDao.find(customerId);

		if (customerEntity == null) {
			throw new AcademyServiceException("Customer with id " + customerId + " is null");
		}

		Date creationDate = GregorianCalendar.getInstance().getTime();

		orderEntity.setCode(orderModel.getCode());
		orderEntity.setCustomer(customerEntity);
		orderEntity.setLastModifiedDate(creationDate);
		orderEntity.setSubmissionDate(creationDate);

		this.orderDao.save(orderEntity);

		orderModel.setId(orderEntity.getId());
		orderModel.setSubmissionDate(creationDate);

		InsertOrderServiceResponse serviceResponse = new InsertOrderServiceResponse();
		serviceResponse.setOrder(orderModel);

		return serviceResponse;
	}

	@Override
	public RetrieveOrderListServiceResponse retrieveOrderList(RetrieveOrderListServiceRequest request)
			throws AcademyServiceException {

		Long customerId = null;
		if ((customerId = request.getCustomerId()) == null) {
			throw new AcademyServiceException("customer id cannot be empty");
		}

		org.fondazioneits.academy.persistence.entity.Customer customerEntity = this.customerDao.find(customerId);
		if (customerEntity == null) {
			throw new AcademyServiceException("Customer not found in database");
		}

		List<org.fondazioneits.academy.persistence.entity.Order> orderEntityList = customerEntity.getOrders();
		Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();

		List<org.fondazioneits.academy.model.Order> orderModelList = new ArrayList<>();
		mapper.map(orderEntityList, orderModelList);

		RetrieveOrderListServiceResponse serviceResponse = new RetrieveOrderListServiceResponse();
		serviceResponse.setOrderList(orderModelList);

		return serviceResponse;
	}

}
