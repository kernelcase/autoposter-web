package com.facebook_autoposter.robot.core.post;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/posts")
public class PostResource {

	@Inject
	private PostEJB postEJB;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createPost(PostDTO postDTO) {
		
		postEJB.createPost(postDTO);
		
		return Response.ok().build();
	}
}
