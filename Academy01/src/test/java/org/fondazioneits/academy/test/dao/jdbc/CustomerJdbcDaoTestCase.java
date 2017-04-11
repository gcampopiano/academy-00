package org.fondazioneits.academy.test.dao.jdbc;

import java.util.Date;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.fondazioneits.academy.feature.customer.dao.CustomerDao;
import org.fondazioneits.academy.persistence.dao.Dinosaur;
import org.fondazioneits.academy.persistence.entity.Customer;
import org.fondazioneits.academy.test.AcademyServiceTestCase;
import org.junit.Assert;
import org.junit.Test;

public class CustomerJdbcDaoTestCase extends AcademyServiceTestCase {

	@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	private EntityManager em;

	@Resource
	private UserTransaction utx;

	@Inject
	@Dinosaur
	private CustomerDao customerDao;

	@Test
	public void retrieveOrderDetailsSuccessful() throws Exception {

		Long custId = null;
		String custName = "Guido" + Math.random();
		String custSurname = "Campopiano" + Math.random();
		Date lastModDate = new Date();

		// inserisco Customer per test
		Customer testCustomer = new Customer();
		testCustomer.setName(custName);
		testCustomer.setSurname(custSurname);
		testCustomer.setLastModifiedDate(lastModDate);

		this.utx.begin();
		this.em.joinTransaction();

		this.em.persist(testCustomer);
		custId = testCustomer.getId();

		this.utx.commit();
		this.em.clear();

		Customer custFromDb = this.customerDao.find(custId);
		Assert.assertNotNull(custFromDb);
		Assert.assertEquals(custId, custFromDb.getId());
		Assert.assertEquals(custName, custFromDb.getName());
		Assert.assertEquals(custSurname, custFromDb.getSurname());
		Assert.assertEquals(lastModDate, custFromDb.getLastModifiedDate());
	}

}
