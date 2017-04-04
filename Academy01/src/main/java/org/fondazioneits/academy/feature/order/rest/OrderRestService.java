package org.fondazioneits.academy.feature.order.rest;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.fondazioneits.academy.feature.order.service.InsertOrderServiceRequest;
import org.fondazioneits.academy.feature.order.service.InsertOrderServiceResponse;
import org.fondazioneits.academy.feature.order.service.OrderService;
import org.fondazioneits.academy.feature.order.service.RetrieveOrderListServiceRequest;
import org.fondazioneits.academy.feature.order.service.RetrieveOrderListServiceResponse;
import org.fondazioneits.academy.rest.AcademyRestServiceException;
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

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public InsertOrderRestServiceResponse insertNewOrder(org.fondazioneits.academy.model.Order order)
			throws AcademyRestServiceException {
		InsertOrderServiceRequest serviceRequest = new InsertOrderServiceRequest();
		serviceRequest.setOrder(order);

		InsertOrderServiceResponse serviceResponse = null;

		try {
			serviceResponse = this.orderService.insertNewOrder(serviceRequest);
		} catch (AcademyServiceException e) {
			this.logger.severe("Following error occurred in insertNewOrder(): " + e.getMessage());
			throw new AcademyRestServiceException(e.getErrorCode());
		}

		InsertOrderRestServiceResponse restResponse = new InsertOrderRestServiceResponse(serviceResponse);

		return restResponse;
	}

}
