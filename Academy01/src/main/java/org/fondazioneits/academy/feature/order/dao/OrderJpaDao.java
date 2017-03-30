package org.fondazioneits.academy.feature.order.dao;

import java.util.List;

import javax.persistence.Query;

import org.fondazioneits.academy.persistence.dao.JPADao;
import org.fondazioneits.academy.persistence.entity.Order;

public class OrderJpaDao extends JPADao<Order> implements OrderDao {

	public OrderJpaDao(Class<Order> type) {
		super(type);
	}

	public OrderJpaDao() {
		this(Order.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> retrieveOrdersByCustomerId(Long customerId) {

		String qlString = "from Order o where o.customer.id = :customerId";
		Query q = this.em.createQuery(qlString);
		q.setParameter("customerId", customerId);

		return q.getResultList();
	}

	@Override
	public List<Order> retrieveAllOrders() {

		String qlString = "from Order";
		Query q = this.em.createQuery(qlString);
		return q.getResultList();
	}

}
