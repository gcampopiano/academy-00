package org.fondazioneits.academy.feature.customer.dao;

import java.util.ArrayList;
import java.util.List;

import org.fondazioneits.academy.persistence.dao.DaoMock;
import org.fondazioneits.academy.persistence.dao.JPADao;
import org.fondazioneits.academy.persistence.entity.Customer;

@DaoMock
public class CustomerDaoMock extends JPADao<Customer> implements CustomerDao {

	public CustomerDaoMock(Class<Customer> type) {
		super(type);
	}

	public CustomerDaoMock() {
		this(Customer.class);
	}

	@Override
	public List<Customer> retrieveCustomerListByName(String name) {
		ArrayList<Customer> customerListMock = new ArrayList<>();

		Customer c1 = new Customer();
		c1.setName("Riccardo");
		c1.setSurname("Licini");

		Customer c2 = new Customer();
		c2.setName("Nicolò");
		c2.setSurname("Cattaneo");

		customerListMock.add(c1);
		customerListMock.add(c2);

		return customerListMock;
	}

}
