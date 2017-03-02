package org.fondazioneits.academy.feature.order.service;

import java.util.List;

import org.fondazioneits.academy.model.Order;
import org.fondazioneits.academy.service.AcademyServiceResponse;

public class RetrieveOrderListServiceResponse implements AcademyServiceResponse {

	private static final long serialVersionUID = -6202893586661929905L;

	private List<Order> orderList = null;

	public RetrieveOrderListServiceResponse() {
		super();
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

}
