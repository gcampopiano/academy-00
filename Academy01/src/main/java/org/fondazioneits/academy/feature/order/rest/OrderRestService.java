package org.fondazioneits.academy.feature.order.rest;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fondazioneits.academy.feature.order.service.OrderService;
import org.fondazioneits.academy.feature.order.service.RetrieveOrderListServiceRequest;
import org.fondazioneits.academy.feature.order.service.RetrieveOrderListServiceResponse;
import org.fondazioneits.academy.service.AcademyServiceException;

@Path("/orders")
@RequestScoped
public class OrderRestService {

	@Inject
	private Logger logger;

	@Inject
	private OrderService orderService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RetrieveOrderListRestServiceResponse retrieveOrderList() {

		RetrieveOrderListServiceRequest serviceRequest = new RetrieveOrderListServiceRequest();
		RetrieveOrderListServiceResponse serviceResponse = null;

		try {
			serviceResponse = this.orderService.retrieveOrderList(serviceRequest);
		} catch (AcademyServiceException e) {
			this.logger.severe("Following error occurred in retrieveOrderList(): " + e.getMessage());
		}

		return new RetrieveOrderListRestServiceResponse(serviceResponse);
	}

}
