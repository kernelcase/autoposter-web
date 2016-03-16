package com.facebook_autoposter.robot.core.job;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TimerService;

@Singleton
public class JobTimeEJB {

	@Resource
	private TimerService timerService;
	
	int i = 0;
	
	@PostConstruct
	public void cycle() {
		 do {
			 if(i % 10000 == 0)
			 System.out.println(" i = " + i++);
		 }while(true);
	}
	
}
