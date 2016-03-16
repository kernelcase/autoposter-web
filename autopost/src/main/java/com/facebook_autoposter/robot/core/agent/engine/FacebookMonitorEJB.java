package com.facebook_autoposter.robot.core.agent.engine;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.inject.Inject;

import com.facebook_autoposter.robot.core.facebook.FacebookDTO;
import com.facebook_autoposter.robot.core.facebook.FacebookEJB;
import com.facebook_autoposter.robot.core.facebook.FacebookStatus;

@Startup
@Singleton
public class FacebookMonitorEJB {

	@Resource
	private TimerService timerService;
	
	@Inject
	private FacebookEJB facebookEJB;
	
	@Inject
	private AgentFacebookEJB agentFacebookEJB;
	
	@Inject
	private AgentEngineEJB agentEngineEJB;
	
	@PostConstruct
	public void start() {
		
		agentFacebookEJB.clearAgents();
		
		run();
	}
	
	public void run() {
		
		TimerConfig timerConfig = new TimerConfig();		
		timerConfig.setPersistent(false);
		timerService.createSingleActionTimer(1 * 1000, timerConfig);
	}
	
	@Timeout
	public void monitor(Timer timer) {		
		/*
		List<FacebookDTO> all = facebookEJB.getAll();
		
		for(FacebookDTO facebookDTO: all) {
			AgentFacebookDTO agentFacebookDTO = agentFacebookEJB.getCurrentAgentFacebook(facebookDTO.getIdFacebook());			
			
			if(agentFacebookDTO == null) {
				agentEngineEJB.run(facebookDTO.getIdFacebook());
			}
		}
		
		run();
		*/
	}
}
