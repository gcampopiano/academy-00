package org.fondazioneits.academy.dao;

import org.fondazioneits.academy.entity.Order;

public class OrderJpaDao extends JPADao<Order> {

	public OrderJpaDao(Class<Order> type) {
		super(type);
	}

	public OrderJpaDao() {
		this(Order.class);
	}

}
