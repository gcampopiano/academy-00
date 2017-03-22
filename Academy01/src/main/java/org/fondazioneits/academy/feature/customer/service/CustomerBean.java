package org.fondazioneits.academy.feature.customer.service;

import java.util.GregorianCalendar;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.fondazioneits.academy.feature.customer.dao.CustomerDao;
import org.fondazioneits.academy.interceptor.logging.Logging;
import org.fondazioneits.academy.persistence.entity.Customer;
import org.fondazioneits.academy.service.AcademyServiceException;

/**
 * Session Bean implementation class CustomerBean
 */
@Stateless
public class CustomerBean implements CustomerService {

	@Inject
	public CustomerDao customerJPADao;

	@Inject
	private Logger log;

	public CustomerBean() {

	}

	@Logging
	public RegisterCustomerServiceResponse registerCustomer(RegisterCustomerServiceRequest request)
			throws AcademyServiceException {

		if ((request.getCustomer().getName() == null) || request.getCustomer().getName().isEmpty()) {
			throw new AcademyServiceException("Name cannot be empty");
		}

		if ((request.getCustomer().getSurname() == null) || request.getCustomer().getSurname().isEmpty()) {
			throw new AcademyServiceException("Surname cannot be empty");
		}

		Customer customerEntity = new Customer();
		customerEntity.setName(request.getCustomer().getName());
		customerEntity.setSurname(request.getCustomer().getSurname());
		customerEntity.setLastModifiedDate(GregorianCalendar.getInstance().getTime());

		this.customerJPADao.save(customerEntity);

		RegisterCustomerServiceResponse response = new RegisterCustomerServiceResponse();
		response.setCustomer(request.getCustomer());
		response.getCustomer().setId(customerEntity.getId());

		return response;
	}

	@Override
	@Logging
	public RegisterCustomerServiceResponse modifyRegisteredCustomer(RegisterCustomerServiceRequest request)
			throws AcademyServiceException {

		org.fondazioneits.academy.model.Customer customerModel = request.getCustomer();

		if ((customerModel.getName() == null) || customerModel.getName().isEmpty()) {
			throw new AcademyServiceException("Name cannot be empty");
		}

		if ((customerModel.getSurname() == null) || customerModel.getSurname().isEmpty()) {
			throw new AcademyServiceException("Surname cannot be empty");
		}

		// In questo istante la entity è "detached"
		org.fondazioneits.academy.persistence.entity.Customer customerEntity = new Customer();

		customerEntity.setId(customerModel.getId());
		customerEntity.setName(customerModel.getName());
		customerEntity.setSurname(customerModel.getSurname());

		// il metodo "update" invoca la merge sull'Entity Manager,
		// restituendo una reference ad una entity "attached"
		customerEntity = this.customerJPADao.update(customerEntity);

		customerEntity.setLastModifiedDate(GregorianCalendar.getInstance().getTime());

		return new RegisterCustomerServiceResponse();
	}

}
