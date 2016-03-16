package com.facebook_autoposter.robot.core.job;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/jobs")
public class JobResource {
	
	@Inject
	private JobEJB jobEJB;
		
	@GET
	@Path("/{idJob}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getJob(@PathParam("idJob")Integer idJob) {
		
		JobDTO jobDTO = jobEJB.getJob(idJob);
		Response response = Response.ok(jobDTO).build();
		return response;
	}
	
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response createDispatch(
			@Context HttpServletRequest request,
			JobDTO jobDTO) {
		
		jobEJB.createJob(jobDTO);
		
		return Response.ok().build();
	}
}
