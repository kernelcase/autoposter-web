package com.facebook_autoposter.robot.core.config;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/config")
public class ConfigResource {
	
	@Inject
	private ConfigEJB configEJB;

	
	@GET	
	@Path("/{key}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getConfig(@PathParam("key") String key) {
		String value = configEJB.getValue(key);
		return Response.ok(value).build();
	}
}
