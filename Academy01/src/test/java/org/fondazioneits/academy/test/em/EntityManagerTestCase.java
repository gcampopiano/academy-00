package org.fondazioneits.academy.test.em;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.UserTransaction;

import org.fondazioneits.academy.entity.Customer;
import org.fondazioneits.academy.test.Academy01TestCase;
import org.junit.Assert;
import org.junit.Test;

public class EntityManagerTestCase extends Academy01TestCase {

	public static final String PERSISTENCE_UNIT_NAME = "Academy01";

	@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	protected EntityManager em;

	@Resource
	private UserTransaction utx;

	@Test
	public void findInEmptyDatabaseReturnsNullEntity() {
		Customer c = this.em.find(Customer.class, new Long(1));
		Assert.assertNull(c);
	}

	@Test(expected = PersistenceException.class)
	public void updateWithoutTransactionThrowsException() {
		Customer c = new Customer();
		c.setName("Guido");
		c.setSurname("Campopiano");
		this.em.persist(c);
	}

	@Test
	public void updateWithActiveTransactionSuccessful() throws Exception {
		this.utx.begin();
		this.em.joinTransaction();

		Customer c = new Customer();
		c.setName("Guido");
		c.setSurname("Campopiano");

		this.em.persist(c);

		// salvo l'id del Customer appena creato
		Long justPersistedCustomerId = c.getId();

		this.utx.commit();
		this.em.clear();

		c = this.em.find(Customer.class, justPersistedCustomerId);
		Assert.assertNotNull(c);

	}

}
