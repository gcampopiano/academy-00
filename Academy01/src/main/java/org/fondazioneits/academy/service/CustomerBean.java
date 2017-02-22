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

	public RegisterCustomerServiceResponse registerCustomer(RegisterCustomerServiceRequest request) {

		Customer customerEntity = new Customer();
		customerEntity.setName(request.getCustomer().getName());
		customerEntity.setSurname(request.getCustomer().getSurname());

		this.customerJPADao.save(customerEntity);
		
		RegisterCustomerServiceResponse response = new RegisterCustomerServiceResponse();
		response.setCustomer(request.getCustomer());
		response.getCustomer().setId(customerEntity.getId());

		return response;
	}

}
