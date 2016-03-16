package com.facebook_autoposter.robot.core.agent.action;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.facebook_autoposter.robot.persistence.AgentActionEntity;
import com.facebook_autoposter.robot.persistence.AgentActionParamEntity;
import com.facebook_autoposter.robot.persistence.AgentActionParamQuery;
import com.facebook_autoposter.robot.persistence.AgentActionQuery;
import com.facebook_autoposter.robot.persistence.FacebookEntity;
import com.facebook_autoposter.robot.persistence.FacebookQuery;

@Stateless
public class AgentActionEJB {
	
	@Inject
	private AgentActionQuery actionQuery;
	
	@Inject
	private FacebookQuery facebookQuery;
	
	@Inject
	private AgentActionParamQuery actionParamQuery;

	public Integer createAction(AgentActionDTO agentActionDTO) {			
		
		String actionName = agentActionDTO.getActionName();
		String actionStatus = ActionStatus.HOLD;
		
		FacebookEntity facebookEntity = facebookQuery.getFacebookByUsername(agentActionDTO.getFacebookUsername());
		
		AgentActionEntity agentActionEntity = new AgentActionEntity();		
		
		agentActionEntity.setActionName(actionName);
		agentActionEntity.setActionStatus(actionStatus);
		agentActionEntity.setFacebook(facebookEntity);
		
		Integer orderNumber = 1;
		Integer idFacebook = facebookEntity.getIdFacebook();
		List<AgentActionEntity> actionOnHoldList = actionQuery.getActionOnHold(idFacebook);
		if(actionOnHoldList.size() > 0) {
			orderNumber = actionOnHoldList.get(actionOnHoldList.size()-1).getOrderNumber() + 1;
		}
		agentActionEntity.setOrderNumber(orderNumber);
		
		actionQuery.save(agentActionEntity);

		for(AgentActionParamDTO actionParamDTO: agentActionDTO.getParams()) {
			
			String param = actionParamDTO.getParam();
			String value = actionParamDTO.getValue();
			
			AgentActionParamEntity agentActionParamEntity = new AgentActionParamEntity();
			agentActionParamEntity.setAgentAction(agentActionEntity);
			agentActionParamEntity.setParam(param);
			agentActionParamEntity.setValue(value);
			
			actionParamQuery.save(agentActionParamEntity);
			
		}
		
		return agentActionEntity.getIdAgentAction();
	}
	
	public AgentActionDTO getAction(Integer idAction) {
		
		AgentActionEntity actionEntity = actionQuery.getActionById(idAction);
		
		if(actionEntity == null) {
			return null;
		}
		
		AgentActionDTO actionDTO = new AgentActionDTO();
		actionDTO.setActionName(actionEntity.getActionName());
		actionDTO.setFacebookUsername(actionEntity.getFacebook().getFacebookUsername());
		actionDTO.setIdAction(idAction);
		actionDTO.setActionStatus(actionEntity.getActionStatus());
		actionDTO.setOrderNumber(actionEntity.getOrderNumber());
		
		List<AgentActionParamDTO> params = new ArrayList<AgentActionParamDTO>();
		
		List<AgentActionParamEntity> actionParamEntityList = actionParamQuery.getParams(idAction);
		for(AgentActionParamEntity actionParamEntity: actionParamEntityList) {
			AgentActionParamDTO actionParamDTO = new AgentActionParamDTO();
			actionParamDTO.setParam(actionParamEntity.getParam());
			actionParamDTO.setValue(actionParamEntity.getValue());
			params.add(actionParamDTO);
		}
		
		actionDTO.setParams(params);
				
		return actionDTO; 
	}
	
	public AgentActionDTO getNextAction(Integer idFacebook) {
		AgentActionEntity actionEntity = actionQuery.getNextAction(idFacebook);
		
		
		AgentActionDTO agentActionDTO = null;
		if(actionEntity != null) {
			agentActionDTO = getAction(actionEntity.getIdAgentAction());
		}
		
		return agentActionDTO;
		
	}
	
	public void updateStatus(Integer idAction, String actionStatus) {
		AgentActionEntity actionEntity = actionQuery.getActionById(idAction);
		actionEntity.setActionStatus(actionStatus);
		actionQuery.save(actionEntity);
	}
	
}
