package org.fondazioneits.academy.feature.order.service;

import org.fondazioneits.academy.service.AcademyService;
import org.fondazioneits.academy.service.AcademyServiceException;

public interface OrderService extends AcademyService {

	public InsertOrderServiceResponse insertNewOrder(InsertOrderServiceRequest request) throws AcademyServiceException;
	
	public RetrieveOrderListServiceResponse retrieveOrderList(RetrieveOrderListServiceRequest request) throws AcademyServiceException;

}
