package org.fondazioneits.academy.dao;

import java.util.List;

import javax.persistence.Query;

import org.fondazioneits.academy.entity.Customer;

public class CustomerJPADao extends JPADao<Customer> implements CustomerDao {

	public CustomerJPADao(Class<Customer> type) {
		super(type);
	}

	public CustomerJPADao() {
		this(Customer.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> retrieveCustomerListByName(String name) {
		String qlString = "from Customer c where c.name = :name";
		Query q = this.em.createQuery(qlString);
		q.setParameter("name", name);
		return q.getResultList();
	}

}
