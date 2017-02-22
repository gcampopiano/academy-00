package org.fondazioneits.academy.test.em;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import org.fondazioneits.academy.persistence.entity.BaseAcademyEntity;
import org.fondazioneits.academy.persistence.entity.Customer;
import org.fondazioneits.academy.persistence.entity.Order;
import org.fondazioneits.academy.test.Academy01TestCase;
import org.jboss.arquillian.test.spi.ArquillianProxyException;
import org.junit.Assert;
import org.junit.Test;

public class EntityManagerTestCase extends Academy01TestCase {

	@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	protected EntityManager em;

	@Resource
	private UserTransaction utx;

	@Test
	public void findInEmptyDatabaseReturnsNullEntity() {
		BaseAcademyEntity c = this.em.find(Customer.class, new Long(Long.MIN_VALUE));
		Assert.assertNull(c);
	}

	@Test(expected = PersistenceException.class)
	public void updateWithNoActiveTransactionThrowsException() {
		Customer c = new Customer();
		c.setName("Guido");
		c.setSurname("Campopiano");

		// questa istruzione genera eccezione perché non vi è una transazione
		// attiva!!!
		this.em.persist(c);
	}

	@Test
	public void createCustomerWithActiveTransactionSuccessful() throws Exception {
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

	@Test(expected = ArquillianProxyException.class)
	public void fetchOrderListWithNoActiveTransactionThrowsException() throws Exception {
		this.utx.begin();
		this.em.joinTransaction();

		Customer c = new Customer();
		c.setName("Federico");
		c.setSurname("Gervasoni");
		c.setOrders(new ArrayList<>());

		Order o1 = new Order();
		o1.setCode("o1Code");
		o1.setCustomer(c);
		o1.setSubmissionDate(GregorianCalendar.getInstance().getTime());

		o1 = c.addOrder(o1);

		this.em.persist(c);
		this.em.persist(o1);

		// salvo l'id del Customer appena creato
		Long justPersistedCustomerId = c.getId();

		this.utx.commit();

		c = this.em.find(Customer.class, justPersistedCustomerId);
		Assert.assertNotNull(c);

		// questa istruzione lancia eccezione perché non vi è una transazione
		// attiva
		// e l'associazione è lazy!!!
		List<Order> orderList = c.getOrders();

		Assert.assertTrue((orderList != null) && !orderList.isEmpty());
	}

	@Test
	public void createCustomerWithOneOrderSuccessful() throws Exception {
		this.utx.begin();
		this.em.joinTransaction();

		Customer c = new Customer();
		c.setName("Federico");
		c.setSurname("Gervasoni");
		c.setOrders(new ArrayList<>());

		Order o1 = new Order();
		o1.setCode("o1Code");
		o1.setCustomer(c);
		o1.setSubmissionDate(GregorianCalendar.getInstance().getTime());

		o1 = c.addOrder(o1);

		this.em.persist(c);
		this.em.persist(o1);

		// salvo l'id del Customer appena creato
		Long justPersistedCustomerId = c.getId();

		this.utx.commit();

		this.utx.begin();
		this.em.joinTransaction();

		c = this.em.find(Customer.class, justPersistedCustomerId);
		Assert.assertNotNull(c);

		Assert.assertTrue(!c.getOrders().isEmpty());

		this.utx.commit();
		this.em.clear();
	}

	@Test
	public void retrieveCustomerListByUsernameSuccessful() {
		String qlString = "from Customer c where c.name = :nameParam";
		Query q = this.em.createQuery(qlString);
		q.setParameter("nameParam", "Guido");

		@SuppressWarnings("unchecked")
		List<Customer> customerList = q.getResultList();
		Assert.assertTrue((customerList != null) && (!customerList.isEmpty()));
	}

	@Test
	public void deleteCustomerByIdSuccessful() throws Exception {
		Long entityId = new Long(2);

		this.utx.begin();
		this.em.joinTransaction();

		BaseAcademyEntity toRemove = this.em.find(Customer.class, entityId);
		this.em.remove(toRemove);

		this.utx.commit();
		this.em.clear();

		BaseAcademyEntity c = this.em.find(Customer.class, entityId);
		Assert.assertNull(c);
	}

	@Test
	public void updateCustomerSuccessful() throws Exception {

		this.utx.begin();
		this.em.joinTransaction();

		Customer toUpdate = this.em.find(Customer.class, new Long(4));

		toUpdate.setName("Oliver");
		toUpdate.setSurname("Medina");

		this.utx.commit();
		this.em.clear();

		Customer c = this.em.find(Customer.class, new Long(4));
		Assert.assertEquals("Oliver", c.getName());
		Assert.assertEquals("Medina", c.getSurname());

	}

}
