package com.facebook_autoposter.robot.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BusinessExceptionHandler implements ExceptionMapper<BusinessException> {
	
	public Response toResponse(BusinessException exception) {
		
		ErrorMessageDTO errorDTO = exception.getErrorMessage();
		if(errorDTO != null) {
			return Response.serverError().entity(errorDTO).build();
		} else {		
			return Response.status(Status.BAD_REQUEST).entity(exception.getMessage()).build();
		}
	}
}