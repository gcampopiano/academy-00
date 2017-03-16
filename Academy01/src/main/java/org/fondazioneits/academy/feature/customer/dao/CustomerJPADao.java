package org.fondazioneits.academy.feature.customer.dao;

import java.util.List;

import javax.enterprise.inject.Default;
import javax.persistence.Query;

import org.fondazioneits.academy.persistence.dao.JPADao;
import org.fondazioneits.academy.persistence.entity.Customer;

@Default
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
