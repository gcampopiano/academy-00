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
import org.fondazioneits.academy.test.AcademyServiceTestCase;
import org.junit.Assert;
import org.junit.Test;

public class CustomerJpaDaoTestCase extends AcademyServiceTestCase {

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

		Long cId = c.getId();

		this.utx.commit();
		this.em.clear();

		Customer cAfterSaving = this.em.find(Customer.class, cId);
		Assert.assertNotNull(cAfterSaving);
		Assert.assertEquals("Peppa", c.getName());
		Assert.assertEquals("Pig", c.getSurname());
	}

	@Test
	public void retrieveCustomerListByNameSuccessful() throws Exception {
		// insert test customers
		String randomName1 = "Guido" + Math.random();
		String randomSurname1 = "Campopiano" + Math.random();

		String randomName2 = "John" + Math.random();
		String randomSurname2 = "Rambo" + Math.random();
		String randomSurname22 = "Rambo" + Math.random();

		this.utx.begin();
		this.em.joinTransaction();

		Customer c1 = new Customer();
		c1.setName(randomName1);
		c1.setSurname(randomSurname1);

		Customer c2 = new Customer();
		c2.setName(randomName2);
		c2.setSurname(randomSurname2);

		Customer c22 = new Customer();
		c22.setName(randomName2);
		c22.setSurname(randomSurname22);

		this.customerJPADao.save(c1);
		this.customerJPADao.save(c2);
		this.customerJPADao.save(c22);

		this.utx.commit();
		this.em.clear();

		List<Customer> customerList1 = this.customerJPADao.retrieveCustomerListByName(randomName1);
		Assert.assertNotNull(customerList1);
		Assert.assertEquals(1, customerList1.size());

		List<Customer> customerList2 = this.customerJPADao.retrieveCustomerListByName(randomName2);
		Assert.assertNotNull(customerList2);
		Assert.assertEquals(2, customerList2.size());
	}

}
