package org.fondazioneits.academy.dao;

import org.fondazioneits.academy.entity.Customer;

import java.util.List;

public interface CustomerDao {

	public List<Customer> retrieveCustomerListByName(String name);

}
