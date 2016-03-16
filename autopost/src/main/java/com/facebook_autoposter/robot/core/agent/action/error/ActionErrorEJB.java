package com.facebook_autoposter.robot.core.agent.action.error;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.facebook_autoposter.robot.persistence.AgentActionEntity;
import com.facebook_autoposter.robot.persistence.AgentActionErrorEntity;
import com.facebook_autoposter.robot.persistence.AgentActionErrorQuery;
import com.facebook_autoposter.robot.persistence.AgentActionQuery;

@Stateless
public class ActionErrorEJB {
	
	@Inject
	private AgentActionErrorQuery actionErrorQuery;
	
	@Inject
	private AgentActionQuery actionQuery;

	public void createActionError(ActionErrorDTO actionErrorDTO) {
		AgentActionErrorEntity actionErrorEntity = new AgentActionErrorEntity();
		
		AgentActionEntity action = actionQuery.getActionById(actionErrorDTO.getIdAction());
		String errorCode = actionErrorDTO.getErrorCode();
		String errorMessage = actionErrorDTO.getErrorMessage();				
		String stacktrace = actionErrorDTO.getStacktrace();
		
		actionErrorEntity.setAgentAction(action);
		actionErrorEntity.setErrorCode(errorCode);
		actionErrorEntity.setErrorMessage(errorMessage);		
		actionErrorEntity.setStacktrace(stacktrace);
		
		actionErrorQuery.save(actionErrorEntity);
	}
}
