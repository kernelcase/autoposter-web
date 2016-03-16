package com.facebook_autoposter.robot.core.facebook.group;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import com.facebook_autoposter.robot.core.agent.action.AgentActionDTO;
import com.facebook_autoposter.robot.core.agent.action.AgentActionEJB;
import com.facebook_autoposter.robot.core.agent.action.ActionName;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class FacebookGroupEJB {

	@Resource
	private EJBContext context;
	
	@Inject
	private AgentActionEJB actionEJB;
	
	public List<FacebookGroupDTO> getGroups(String facebookUsername) {
		
		
		AgentActionDTO actionDTO = new AgentActionDTO();
		actionDTO.setActionName(ActionName.GROUPS);
		actionDTO.setFacebookUsername(facebookUsername);
		actionEJB.createAction(actionDTO);
		
		return null;
		
		/*
		
		WebDriver webDriver = webDriverManagerEJB.useWebDriver(facebookUsername);
		
		UserGroupsTask userGroupsTask = new UserGroupsTask();
		userGroupsTask.setFacebookUsername(facebookUsername);
		userGroupsTask.setWebDriver(webDriver);
		
		try {
			userGroupsTask.run();
		} catch (Exception ex) {
			webDriverManagerEJB.freeWebDriver(facebookUsername);
			throw ex;
		}
		
		webDriverManagerEJB.freeWebDriver(facebookUsername);
		
		List<Group> groups = userGroupsTask.getGroups();
		List<GroupDTO> groupDTOList = new ArrayList<GroupDTO>();
		
		for(Group group: groups) {
			GroupDTO groupDTO = new GroupDTO();
			groupDTO.setGroupName(group.getGroupName());
			groupDTO.setGroupUrl(group.getGroupUrl());
			groupDTO.setIdGroup(group.getIdGroup());
			groupDTOList.add(groupDTO);
		}
		
		return groupDTOList;			
		
		*/
	}
}
