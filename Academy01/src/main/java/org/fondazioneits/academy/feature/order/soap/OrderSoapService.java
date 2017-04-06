package org.fondazioneits.academy.feature.order.soap;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.fondazioneits.academy.model.Order;
import org.fondazioneits.academy.model.OrderStatus;
import org.fondazioneits.academy.soap.AcademySoapService;

/**
 * Solo a scopo esemplificativo
 * 
 * @author guido.campopiano
 *
 */

@WebService
public class OrderSoapService implements AcademySoapService {

	/**
	 * Example
	 * 
	 * @param name
	 * @return
	 */
	@WebMethod(exclude = true)
	public String sayHello(String name) {
		return "Hi, " + name + "!";
	}

	@WebMethod
	public Order getOrderById(Long orderId) {
		Order o = new Order();
		o.setId(orderId);
		o.setCode("aCode");
		o.setOrderStatus(OrderStatus.NEW);
		o.setSubmissionDate(new Date());
		return o;
	}

}
