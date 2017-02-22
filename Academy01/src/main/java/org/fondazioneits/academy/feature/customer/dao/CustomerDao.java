package org.fondazioneits.academy.feature.customer.dao;

import java.util.List;

import org.fondazioneits.academy.persistence.entity.Customer;

public interface CustomerDao {

	public List<Customer> retrieveCustomerListByName(String name);

}
