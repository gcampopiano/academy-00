package org.fondazioneits.academy.test.dao.jpa;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.UserTransaction;

import org.fondazioneits.academy.feature.customer.dao.CustomerDao;
import org.fondazioneits.academy.persistence.dao.DaoMock;
import org.fondazioneits.academy.persistence.entity.BaseAcademyEntity;
import org.fondazioneits.academy.persistence.entity.Customer;
import org.fondazioneits.academy.test.Academy01TestCase;
import org.junit.Assert;
import org.junit.Test;

public class CustomerJpaDaoTestCase extends Academy01TestCase {

	@Inject
	public CustomerDao customerJPADao;

	@Inject
	@DaoMock
	public CustomerDao customerDaoMock;

	@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	protected EntityManager em;

	@Resource
	private UserTransaction utx;

	@Test
	public void findCustomerByPrimaryKeySuccessful() {
		BaseAcademyEntity c = this.customerJPADao.find(new Long(1));
		Assert.assertNotNull(c);
	}

	@Test(expected = PersistenceException.class)
	public void saveNewCustomerWithNoActiveTransactionThrowsException() {
		Customer c = new Customer();
		c.setName("Peppa");
		c.setSurname("Pig");

		// questa istruzione genera eccezione perché non vi è una transazione
		// attiva!!!
		this.customerJPADao.save(c);
	}

	@Test
	public void saveNewCustomerWithActiveTransactionSuccessful() throws Exception {
		this.utx.begin();
		this.em.joinTransaction();

		Customer c = new Customer();
		c.setName("Peppa");
		c.setSurname("Pig");

		this.customerJPADao.save(c);

		this.utx.commit();
		this.em.clear();
	}

	@Test
	public void retrieveCustomerListByNameSuccessful() {
		List<Customer> customerList = this.customerJPADao.retrieveCustomerListByName("Guido");
		for (Customer currCustomer : customerList) {
			System.out.println("currCustomer.getName()=" + currCustomer.getName());
		}

		List<Customer> customerListMock = this.customerDaoMock.retrieveCustomerListByName("Guido");
		for (Customer currCustomer : customerListMock) {
			System.out.println("currCustomer.getName()=" + currCustomer.getName());
		}

	}

}
