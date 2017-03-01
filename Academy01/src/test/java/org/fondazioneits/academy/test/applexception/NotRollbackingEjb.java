package org.fondazioneits.academy.test.applexception;

import java.util.GregorianCalendar;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.fondazioneits.academy.feature.customer.dao.CustomerDao;
import org.fondazioneits.academy.persistence.entity.Customer;

@Stateless
public class NotRollbackingEjb {

	@Inject
	private CustomerDao customerDao;

	public void registerNewCustomer() throws NotRollbackingCheckedException {

		org.fondazioneits.academy.persistence.entity.Customer customerEntity = new Customer();
		customerEntity.setName("Pippo");
		customerEntity.setSurname("Paperino");
		customerEntity.setLastModifiedDate(GregorianCalendar.getInstance().getTime());

		this.customerDao.save(customerEntity);

		throw new NotRollbackingCheckedException(customerEntity.getId());
	}

}
