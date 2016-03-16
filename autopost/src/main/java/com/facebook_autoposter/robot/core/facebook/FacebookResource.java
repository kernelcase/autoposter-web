package com.facebook_autoposter.robot.core.facebook;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/facebook")
public class FacebookResource {
	
	@Inject
	private FacebookEJB facebookEJB;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createFacebook(FacebookDTO facebookDTO) {
		facebookEJB.createFacebook(facebookDTO);
		
		return Response.ok().build();
	}
}
