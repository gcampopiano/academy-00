package org.fondazioneits.academy.feature.order.dao;

import org.fondazioneits.academy.persistence.dao.JPADao;
import org.fondazioneits.academy.persistence.entity.Order;

public class OrderJpaDao extends JPADao<Order> {

	public OrderJpaDao(Class<Order> type) {
		super(type);
	}

	public OrderJpaDao() {
		this(Order.class);
	}

}
