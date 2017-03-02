package org.fondazioneits.academy.feature.order.dao;

import java.util.List;

import org.fondazioneits.academy.persistence.dao.AcademyDao;
import org.fondazioneits.academy.persistence.entity.Order;

public interface OrderDao extends AcademyDao<Order> {

	public List<Order> retrieveOrdersByCustomerId(Long customerId);

}
