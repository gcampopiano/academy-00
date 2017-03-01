package org.fondazioneits.academy.test.applexception;

import javax.ejb.EJB;
import javax.inject.Inject;

import org.fondazioneits.academy.feature.customer.dao.CustomerDao;
import org.fondazioneits.academy.test.Academy01TestCase;
import org.junit.Assert;
import org.junit.Test;

public class ApplicationExceptionTestCase extends Academy01TestCase {

	@EJB
	private RollbackingEjb rollbackingEjb;

	@EJB
	private NotRollbackingEjb notRollbackingEjb;

	@Inject
	private CustomerDao customerDao;

	@Test
	public void testRollbackingEjb() {

		Long customerId = null;

		boolean exceptionThrown = false;
		try {
			this.rollbackingEjb.registerNewCustomer();
		} catch (RollbackingCheckedException e) {
			exceptionThrown = true;
			customerId = e.getCustomerId();
		}

		if (!exceptionThrown) {
			Assert.fail();
		}

		Assert.assertNotNull(customerId);

		org.fondazioneits.academy.persistence.entity.Customer customerEntity = this.customerDao.find(customerId);
		Assert.assertNull(customerEntity);

	}

	@Test
	public void testNotRollbackingEjb() {

		Long customerId = null;

		boolean exceptionThrown = false;
		try {
			this.notRollbackingEjb.registerNewCustomer();
		} catch (NotRollbackingCheckedException e) {
			exceptionThrown = true;
			customerId = e.getCustomerId();
		}

		if (!exceptionThrown) {
			Assert.fail();
		}

		Assert.assertNotNull(customerId);

		org.fondazioneits.academy.persistence.entity.Customer customerEntity = this.customerDao.find(customerId);
		Assert.assertNotNull(customerEntity);

	}

}
