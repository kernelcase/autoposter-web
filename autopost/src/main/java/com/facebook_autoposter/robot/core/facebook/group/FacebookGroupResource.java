package com.facebook_autoposter.robot.core.facebook.group;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/{facebookUsername}/groups")
public class FacebookGroupResource {
	
	@Inject
	private FacebookGroupEJB facabookGroupEJB;	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGroups(@PathParam("facebookUsername")String facebookUsername) {
		
		List<FacebookGroupDTO> groups = facabookGroupEJB.getGroups(facebookUsername);
		
		return Response.ok(groups).build();
	}
}
