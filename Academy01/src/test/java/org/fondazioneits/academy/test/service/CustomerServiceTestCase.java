package org.fondazioneits.academy.test.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.fondazioneits.academy.feature.customer.dao.CustomerDao;
import org.fondazioneits.academy.feature.customer.service.CustomerService;
import org.fondazioneits.academy.feature.customer.service.RegisterCustomerServiceRequest;
import org.fondazioneits.academy.feature.customer.service.RegisterCustomerServiceResponse;
import org.fondazioneits.academy.model.Customer;
import org.fondazioneits.academy.service.AcademyServiceException;
import org.fondazioneits.academy.test.AcademyServiceTestCase;
import org.junit.Assert;
import org.junit.Test;

public class CustomerServiceTestCase extends AcademyServiceTestCase {

	@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	private EntityManager em;

	@Resource
	private UserTransaction utx;

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

		org.fondazioneits.academy.persistence.entity.Customer justCreatedCustomer = this.em.find(
				org.fondazioneits.academy.persistence.entity.Customer.class, serviceResponse.getCustomer().getId());
		Assert.assertNotNull(justCreatedCustomer);
		Assert.assertNotNull(justCreatedCustomer.getLastModifiedDate());

	}

	@Test
	public void updateSurnameOfAlreadyRegisteredCustomerSuccessful() throws Exception {

		String oldName = "Oliver";
		Date lastModifiedDate = new Date();

		this.utx.begin();
		this.em.joinTransaction();

		org.fondazioneits.academy.persistence.entity.Customer c0 = new org.fondazioneits.academy.persistence.entity.Customer();
		c0.setName("Oliver");
		c0.setSurname("Medina");
		c0.setLastModifiedDate(lastModifiedDate);

		this.customerDao.save(c0);

		Long c0Id = c0.getId();

		this.utx.commit();
		this.em.clear();

		org.fondazioneits.academy.model.Customer customer = new Customer();
		customer.setName("Oliver");
		customer.setSurname("Mucheduky");
		customer.setId(c0Id);

		RegisterCustomerServiceRequest serviceRequest = new RegisterCustomerServiceRequest();
		serviceRequest.setCustomer(customer);

		RegisterCustomerServiceResponse serviceResponse = null;

		try {
			serviceResponse = this.customerService.modifyRegisteredCustomer(serviceRequest);
		} catch (AcademyServiceException e) {
			Assert.fail();
		}

		Assert.assertNotNull(serviceResponse);

		org.fondazioneits.academy.model.Customer cz = serviceResponse.getCustomer();
		Assert.assertNotNull(cz);
		Assert.assertTrue((cz.getName() != null) && !cz.getName().isEmpty() && cz.getName().equals("Oliver"));
		Assert.assertTrue(
				(cz.getSurname() != null) && !cz.getSurname().isEmpty() && cz.getSurname().equals("Mucheduky"));

		org.fondazioneits.academy.persistence.entity.Customer justModifiedCustomer = this.em
				.find(org.fondazioneits.academy.persistence.entity.Customer.class, c0Id);

		Assert.assertNotNull(justModifiedCustomer);
		Assert.assertEquals(oldName, justModifiedCustomer.getName());
		Assert.assertEquals("Mucheduky", justModifiedCustomer.getSurname());
		Assert.assertTrue(justModifiedCustomer.getLastModifiedDate().after(lastModifiedDate));

	}

	@Test
	public void createCustomerWithoutNameThrowsException() {

		String customerSurname = "Nicolò" + Math.random();

		org.fondazioneits.academy.model.Customer customer = new Customer();
		customer.setSurname(customerSurname);

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

		@SuppressWarnings("unchecked")
		List<org.fondazioneits.academy.persistence.entity.Customer> customerList = this.em
				.createQuery("from Customer c where c.surname = :surname").setParameter("surname", customerSurname)
				.getResultList();

		Assert.assertTrue((customerList == null) || (customerList.isEmpty()));
	}

	@Test
	public void createCustomerWithoutSurnameThrowsException() {

		String customerName = "Nicolò" + Math.random();

		org.fondazioneits.academy.model.Customer customer = new Customer();
		customer.setName(customerName);

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
				.retrieveCustomerListByName(customerName);

		Assert.assertTrue((customerList == null) || (customerList.isEmpty()));
	}

}
