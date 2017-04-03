package org.fondazioneits.academy.rest;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AcademyRestServiceExceptionMapper implements ExceptionMapper<AcademyRestServiceException> {

	@Override
	public Response toResponse(AcademyRestServiceException restServiceException) {
		return Response.status(restServiceException.getStatus()).entity(restServiceException.getMessage())
				.type(MediaType.APPLICATION_JSON).build();
	}

}
