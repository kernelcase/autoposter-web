package com.facebook_autoposter.robot.core.agent.action;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/facebook/{facebookUsername}/actions")
public class AgentActionResource {

	@Inject
	private AgentActionEJB agentActionEJB;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createAction(AgentActionDTO agentActionDTO) {
		
		Integer idAgentAction = agentActionEJB.createAction(agentActionDTO);
		
		return Response.ok(idAgentAction).build();
	}
}
