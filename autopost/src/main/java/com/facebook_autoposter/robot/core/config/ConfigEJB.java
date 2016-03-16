package com.facebook_autoposter.robot.core.config;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import com.facebook_autoposter.robot.persistence.ConfigEntity;
import com.facebook_autoposter.robot.persistence.ConfigQuery;

@Stateless
public class ConfigEJB {

	@Inject
	private ConfigQuery configQuery;
	
	public String getValue(String key) {
		ConfigEntity configEntity = null;
		try {
			configEntity = configQuery.getByKey(key);
		} catch(NoResultException ex) {
			
		}
		
		return configEntity.getValue();
	}
}
