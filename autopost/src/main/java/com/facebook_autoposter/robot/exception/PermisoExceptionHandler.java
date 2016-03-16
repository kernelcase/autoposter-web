package com.facebook_autoposter.robot.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class PermisoExceptionHandler implements ExceptionMapper<PermisoException> {
	
	public Response toResponse(PermisoException exception) {
		return Response.status(Status.FORBIDDEN).entity(exception.getMessage()).build();	
	}
}