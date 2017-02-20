package org.fondazioneits.academy.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.fondazioneits.academy.dao.JPADao;
import org.fondazioneits.academy.entity.Customer;

/**
 * Session Bean implementation class CustomerBean
 */
@Stateless
public class CustomerBean implements CustomerService {

	@Inject
	public JPADao<Customer> customerJPADao;

	public CustomerBean() {

	}

	public CreateCustomerServiceResponse createCustomer(CreateCustomerServiceRequest request) {

		Customer customerEntity = new Customer();
		customerEntity.setName(request.getCustomer().getName());
		customerEntity.setSurname(request.getCustomer().getSurname());

		this.customerJPADao.save(customerEntity);
		
		CreateCustomerServiceResponse response = new CreateCustomerServiceResponse();
		response.setCustomer(request.getCustomer());
		response.getCustomer().setId(customerEntity.getId());

		return response;
	}

}
