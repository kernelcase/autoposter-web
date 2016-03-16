package com.facebook_autoposter.robot.core.agent.engine;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.facebook_autoposter.robot.persistence.AgentFacebookEntity;
import com.facebook_autoposter.robot.persistence.AgentFacebookQuery;
import com.facebook_autoposter.robot.persistence.FacebookEntity;
import com.facebook_autoposter.robot.persistence.FacebookQuery;

@Stateless
public class AgentFacebookEJB {
	
	@Inject
	private FacebookQuery facebookQuery;

	@Inject
	private AgentFacebookQuery agentFacebookQuery;
	
	public void createAgentFacebook(AgentFacebookDTO agentFacebookDTO) {
		
		FacebookEntity facebookEntity = facebookQuery.getFacebookById(agentFacebookDTO.getIdFacebook());
		
		AgentFacebookEntity agentFacebookEntity = new AgentFacebookEntity();
		agentFacebookEntity.setFacebook(facebookEntity);
		agentFacebookEntity.setCurrent(agentFacebookDTO.getCurrent());
		agentFacebookEntity.setLastConnection(agentFacebookDTO.getLastConnection());
		agentFacebookEntity.setLoggedIn(agentFacebookDTO.getLoggedIn());
		agentFacebookQuery.save(agentFacebookEntity);
				
	}
	
	public void clearAgents() {
		
		List<AgentFacebookEntity> currentList = agentFacebookQuery.getCurrentList();
		
		for(AgentFacebookEntity agentFacebookEntity: currentList) {
			agentFacebookEntity.setCurrent(false);
			agentFacebookQuery.update(agentFacebookEntity);
		}
	}
	
	public AgentFacebookDTO getCurrentAgentFacebook(Integer idFacebook) {
		
		
		
		AgentFacebookDTO agentFacebookDTO = null;
		
		AgentFacebookEntity agentFacebookEntity = agentFacebookQuery.getCurrent(idFacebook);
		if(agentFacebookEntity != null) {
			agentFacebookDTO = new AgentFacebookDTO();
			agentFacebookDTO.setIdAgentFacebook(agentFacebookEntity.getIdAgentFacebook());
			agentFacebookDTO.setIdFacebook(idFacebook);
			agentFacebookDTO.setLastConnection(agentFacebookEntity.getLastConnection());
			agentFacebookDTO.setLoggedIn(agentFacebookEntity.getLoggedIn());
		}
		
		return agentFacebookDTO;
	}
	
}
