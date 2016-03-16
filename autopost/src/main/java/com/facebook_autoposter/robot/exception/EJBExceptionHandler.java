package com.facebook_autoposter.robot.exception;


import javax.ejb.EJBException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EJBExceptionHandler implements ExceptionMapper<EJBException> {
	
	public Response toResponse(EJBException exception) {
		return Response.status(Status.BAD_REQUEST).entity(exception.getMessage()).build();	
	}
}
