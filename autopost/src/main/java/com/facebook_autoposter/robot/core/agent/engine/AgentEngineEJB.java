package com.facebook_autoposter.robot.core.agent.engine;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.inject.Inject;

import org.joda.time.DateTime;
import org.openqa.selenium.WebDriver;

import com.facebook_autoposter.robot.core.agent.action.ActionName;
import com.facebook_autoposter.robot.core.agent.action.ActionStatus;
import com.facebook_autoposter.robot.core.agent.action.AgentActionDTO;
import com.facebook_autoposter.robot.core.agent.action.AgentActionEJB;
import com.facebook_autoposter.robot.core.agent.action.error.ActionErrorDTO;
import com.facebook_autoposter.robot.core.agent.action.error.ActionErrorEJB;
import com.facebook_autoposter.robot.core.facebook.FacebookDTO;
import com.facebook_autoposter.robot.core.facebook.FacebookEJB;
import com.facebook_autoposter.robot.core.facebook.FacebookStatus;
import com.facebook_autoposter.robot.util.StacktraceUtil;

@Stateless
public class AgentEngineEJB {
	
	@Resource
	private TimerService timerService;
	
	@Inject
	private AgentFacebookEJB agentFacebookEJB;
	
	@Inject
	private AgentActionEJB actionEJB;
	
	@Inject
	private ActionErrorEJB actionErrorEJB;
	
	@Inject
	private FacebookEJB facebookEJB;
	
	@Inject
	private WebBrowserEJB webBrowserEJB;

	public void run(Integer idFacebook) {
		
		// Open the browser and log in facebook
		FacebookDTO facebookDTO = facebookEJB.getFacebook(idFacebook);
		
		WebDriver webBrowser = webBrowserEJB.get(idFacebook);
		
		// The login task
		LoginTask loginTask = new LoginTask();
					
		// Configure the login task
		loginTask.setWebDriver(webBrowser);
		loginTask.setFacebookUsername(facebookDTO.getFacebookUsername());
		loginTask.setFacebookPassword(facebookDTO.getFacebookPassword());		
		
		boolean loggedIn = true;
		try {
			loginTask.run();
			loggedIn = loginTask.isInSession();
		} catch(Exception ex) {
			loggedIn = false;
		}
		
		if(loggedIn) {

			// Create the agent
			DateTime lastConnection = DateTime.now();
			AgentFacebookDTO agentFacebookDTO = new AgentFacebookDTO();
			agentFacebookDTO.setIdFacebook(idFacebook);
			agentFacebookDTO.setLoggedIn(true);
			agentFacebookDTO.setLastConnection(lastConnection.toDate());
			agentFacebookDTO.setCurrent(true);
			
			agentFacebookEJB.createAgentFacebook(agentFacebookDTO);
			
			facebookEJB.changeStatus(idFacebook, FacebookStatus.VERIFIED);
			
			TimerConfig timerConfig = new TimerConfig();		
			timerConfig.setPersistent(false);
			timerConfig.setInfo(idFacebook);
			timerService.createSingleActionTimer(1 * 1000, timerConfig);
		} else {
			facebookEJB.changeStatus(idFacebook, FacebookStatus.INVALID_CREDENTIALS);
			
			webBrowser.close();
			webBrowserEJB.remove(idFacebook);
			
		}
		
	}
	
	@Timeout
	public void monitor(Timer timer) {
		
		Integer idFacebook = (Integer) timer.getInfo();			
		
		// Gets the current action
		AgentActionDTO actionDTO = actionEJB.getNextAction(idFacebook);		
		
		if(actionDTO != null) {
			
			// Gets the web browser
			WebDriver webDriver = webBrowserEJB.get(idFacebook);
			
			if(actionDTO.getActionName().equals(ActionName.LOGIN)) {
										
				// The login task
				LoginTask loginTask = new LoginTask();
				
				// Sets the web browser
				loginTask.setWebDriver(webDriver);
				
				// Gets the facebook credentials
				FacebookDTO facebookDTO = facebookEJB.getFacebook(idFacebook);
				loginTask.setFacebookUsername(facebookDTO.getFacebookUsername());
				loginTask.setFacebookPassword(facebookDTO.getFacebookPassword());
				
				// Do the login task
				try {
					loginTask.run();
				} catch(Exception ex) {
					
					// Save the error on the login action
					ActionErrorDTO actionErrorDTO = new ActionErrorDTO();
					actionErrorDTO.setErrorCode("R0100");
					actionErrorDTO.setErrorMessage(ex.getMessage());
					actionErrorDTO.setIdAction(actionDTO.getIdAction());
					actionErrorDTO.setStacktrace(StacktraceUtil.toString(ex.getStackTrace()));
					
					actionErrorEJB.createActionError(actionErrorDTO);
				}
				
				// Finish the action
				actionEJB.updateStatus(actionDTO.getIdAction(), ActionStatus.DONE);
			}
		}
	//	run(idFacebook);
		System.out.println("run recursivo");
	}
	
	
}
