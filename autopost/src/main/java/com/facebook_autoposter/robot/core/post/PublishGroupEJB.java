package com.facebook_autoposter.robot.core.post;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.transaction.UserTransaction;

import org.openqa.selenium.WebDriver;

import com.facebook_autoposter.robot.persistence.PostGroupEntity;
import com.facebook_autoposter.robot.persistence.PostGroupQuery;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class PublishGroupEJB {
	
	@Resource
	private EJBContext context;
	
	@Inject
	private PostGroupQuery postGroupQuery;
	
	public void publish(Integer idPostGroup) {
		System.out.println("Publish " + idPostGroup);
	//	timerService.createTimer(1 * 1000, idPostGroup);
	}
	
	@Timeout
	public void monitor(Timer timer) {
		UserTransaction tx = context.getUserTransaction();
		
		Integer idPostGroup = (Integer) timer.getInfo();
		
		PostGroupEntity postGroupEntity = null;
		try {
			tx.begin();
			postGroupEntity = null;
			postGroupEntity.getPost().getFacebook().getFacebookUsername();
			tx.commit();
		} catch(Exception ex) {
			try {
				tx.rollback();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		String facebookUsername = postGroupEntity.getPost().getFacebook().getFacebookUsername();
		String groupUrl = postGroupEntity.getGroupUrl();
		String photoPath = null;
		String message = postGroupEntity.getPost().getDescription();
		
		WebDriver webDriver = null;//webDriverManagerEJB.useWebDriver(facebookUsername);
		
		PostGroupTask postGroupTask = new PostGroupTask();
		postGroupTask.setWebDriver(webDriver);
		postGroupTask.setGroupUrl(groupUrl);
		postGroupTask.setPhotoPath(photoPath);
		postGroupTask.setMessage(message);
		postGroupTask.run();
		
		TimerService timerService = context.getTimerService();
		timerService.createTimer(1 * 1000, postGroupEntity.getIdPostGroup());
	}
}

