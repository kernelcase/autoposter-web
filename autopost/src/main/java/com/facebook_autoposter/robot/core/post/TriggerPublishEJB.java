package com.facebook_autoposter.robot.core.post;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.transaction.UserTransaction;

import com.facebook_autoposter.robot.persistence.PostEntity;
import com.facebook_autoposter.robot.persistence.PostQuery;

@Startup
@Singleton
@TransactionManagement(TransactionManagementType.BEAN)
public class TriggerPublishEJB {

	@Resource
	private EJBContext context;
	
	@Inject
	private PublishGroupEJB publishGroupEJB;
	
	@Inject
	private PostQuery postQuery;
	
	@PostConstruct
	public void start() {
		TimerService timerService = context.getTimerService();
		
		TimerConfig timerConfig = new TimerConfig();
		timerConfig.setPersistent(false);
		timerService.createSingleActionTimer(1 * 1000, timerConfig);
		/*
		UserTransaction tx = context.getUserTransaction();
		
		List<PostEntity> postOnHoldList = null;
		try {
			tx.begin();
			postOnHoldList = postQuery.getPostOnHold();
			tx.commit();
		} catch(Exception ex) {
			try {
				tx.rollback();
				throw new RuntimeException(ex);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		for(PostEntity postEntity: postOnHoldList) {
			publishGroupEJB.publish(postEntity.getIdPost());
		}
		
		*/
	}
	
	@Timeout
	public void trigger(Timer timer) {
	//	System.out.println(" verificar los pendientes ");
		TimerService timerService = context.getTimerService();
		TimerConfig timerConfig = new TimerConfig();
		timerConfig.setPersistent(false);
		timerService.createSingleActionTimer(1 * 1000, timerConfig);
	}
}
