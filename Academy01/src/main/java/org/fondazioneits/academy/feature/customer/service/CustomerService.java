package org.fondazioneits.academy.feature.customer.service;

import org.fondazioneits.academy.service.AcademyService;
import org.fondazioneits.academy.service.AcademyServiceException;

public interface CustomerService extends AcademyService {

	public RegisterCustomerServiceResponse registerCustomer(RegisterCustomerServiceRequest request)
			throws AcademyServiceException;

	public RegisterCustomerServiceResponse modifyRegisteredCustomer(RegisterCustomerServiceRequest request)
			throws AcademyServiceException;

}
