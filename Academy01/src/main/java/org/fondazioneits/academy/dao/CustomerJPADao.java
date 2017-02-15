package org.fondazioneits.academy.dao;

import org.fondazioneits.academy.entity.Customer;

public class CustomerJPADao extends JPADao<Customer> {

	public CustomerJPADao(Class<Customer> type) {
		super(type);
	}

	public CustomerJPADao() {
		this(Customer.class);
	}

}
