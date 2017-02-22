package org.fondazioneits.academy.test.service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fondazioneits.academy.feature.customer.dao.CustomerDao;
import org.fondazioneits.academy.feature.customer.service.CustomerService;
import org.fondazioneits.academy.feature.customer.service.RegisterCustomerServiceRequest;
import org.fondazioneits.academy.feature.customer.service.RegisterCustomerServiceResponse;
import org.fondazioneits.academy.model.Customer;
import org.fondazioneits.academy.service.AcademyServiceException;
import org.fondazioneits.academy.test.Academy01TestCase;
import org.junit.Assert;
import org.junit.Test;

public class CustomerServiceTestCase extends Academy01TestCase {

	@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	private EntityManager em;

	@EJB
	private CustomerService customerService;

	@Inject
	private CustomerDao customerDao;

	@Test
	public void createCustomerSuccessful() {

		String name = "Oliver";
		String surname = "Medina";

		org.fondazioneits.academy.model.Customer customer = new Customer();
		customer.setName(name);
		customer.setSurname(surname);

		RegisterCustomerServiceRequest serviceRequest = new RegisterCustomerServiceRequest();
		serviceRequest.setCustomer(customer);

		RegisterCustomerServiceResponse serviceResponse = null;
		try {
			serviceResponse = this.customerService.registerCustomer(serviceRequest);
		} catch (AcademyServiceException e) {
			Assert.fail();
		}

		Assert.assertNotNull(serviceResponse);
		Assert.assertNotNull(serviceResponse.getCustomer());
		Assert.assertEquals(name, serviceResponse.getCustomer().getName());
		Assert.assertEquals(surname, serviceResponse.getCustomer().getSurname());
		Assert.assertNotNull(serviceResponse.getCustomer().getId());

		org.fondazioneits.academy.persistence.entity.Customer justCreatedCustomer = this.em
				.find(org.fondazioneits.academy.persistence.entity.Customer.class, serviceResponse.getCustomer().getId());
		Assert.assertNotNull(justCreatedCustomer);
		Assert.assertNotNull(justCreatedCustomer.getLastModifiedDate());

	}

	@Test
	public void updateAlreadyRegisteredCustomerSuccessful() {

		org.fondazioneits.academy.persistence.entity.Customer customerToModify = this.em
				.find(org.fondazioneits.academy.persistence.entity.Customer.class, new Long(28));

		String oldName = customerToModify.getName();
		String oldSurname = customerToModify.getSurname();
		Date lastModifiedDate = customerToModify.getLastModifiedDate();

		org.fondazioneits.academy.model.Customer customer = new Customer();
		customer.setName("Oliver");
		customer.setSurname("Mucheduky");
		customer.setId(new Long(28));

		RegisterCustomerServiceRequest serviceRequest = new RegisterCustomerServiceRequest();
		serviceRequest.setCustomer(customer);

		try {
			this.customerService.modifyRegisteredCustomer(serviceRequest);
		} catch (AcademyServiceException e) {
			Assert.fail();
		}

		this.em.clear();

		org.fondazioneits.academy.persistence.entity.Customer justModifiedCustomer = this.em
				.find(org.fondazioneits.academy.persistence.entity.Customer.class, new Long(28));

		Assert.assertNotNull(justModifiedCustomer);
		Assert.assertEquals(oldName, justModifiedCustomer.getName());
		Assert.assertEquals("Mucheduky", justModifiedCustomer.getSurname());
		Assert.assertTrue(justModifiedCustomer.getLastModifiedDate().after(lastModifiedDate));

	}

	@Test
	public void createCustomerWithoutNameThrowsException() {

		org.fondazioneits.academy.model.Customer customer = new Customer();
		customer.setName("Nicolò");

		RegisterCustomerServiceRequest serviceRequest = new RegisterCustomerServiceRequest();
		serviceRequest.setCustomer(customer);

		boolean exceptionThrown = false;
		try {
			this.customerService.registerCustomer(serviceRequest);
		} catch (AcademyServiceException e) {
			exceptionThrown = true;
		}

		if (!exceptionThrown) {
			Assert.fail();
		}

		List<org.fondazioneits.academy.persistence.entity.Customer> customerList = this.customerDao
				.retrieveCustomerListByName("Nicolò");

		Assert.assertTrue((customerList == null) || (customerList.isEmpty()));
	}

}
