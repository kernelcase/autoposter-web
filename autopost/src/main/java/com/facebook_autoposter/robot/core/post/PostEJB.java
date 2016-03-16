package com.facebook_autoposter.robot.core.post;

import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.joda.time.DateTime;

import com.facebook_autoposter.robot.exception.BusinessException;
import com.facebook_autoposter.robot.exception.Validation;
import com.facebook_autoposter.robot.persistence.FacebookEntity;
import com.facebook_autoposter.robot.persistence.FacebookQuery;
import com.facebook_autoposter.robot.persistence.PostEntity;
import com.facebook_autoposter.robot.persistence.PostGroupEntity;
import com.facebook_autoposter.robot.persistence.PostGroupQuery;
import com.facebook_autoposter.robot.persistence.PostQuery;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class PostEJB {

	@Resource
	private EJBContext context;
	
	@Inject
	private PostQuery postQuery;
	
	@Inject
	private FacebookQuery facebookQuery;
	
	@Inject
	private PostGroupQuery postGroupQuery;
	
	public void createPost(PostDTO postDTO) {
		
		Validation.required()			
			.setParam("facebookUsername", postDTO.getFacebookUsername())
			.setParam("idPost", postDTO.getIdPost())
			.setParam("description", postDTO.getDescription())
			.setParam("name", postDTO.getName())
			.setParam("photo", postDTO.getPhoto())
			.setParam("groups", postDTO.getGroups())
			.validate();
		
		UserTransaction tx = context.getUserTransaction();
		
		try {
			tx.begin();
			
			String description = postDTO.getDescription();
			String facebookUsername = postDTO.getFacebookUsername();
			
			Integer idPost = postDTO.getIdPost();
			String link = postDTO.getLink();
			String name = postDTO.getName();
			Date nextPublish = DateTime.now().toDate();
			String photo = postDTO.getPhoto();
			
			FacebookEntity facebookEntity = null;
			try {
				facebookEntity = facebookQuery.getFacebookByUsername(facebookUsername);
			} catch(NoResultException ex) {
				
			}
			boolean facebookExists = facebookEntity != null;
			if(facebookExists) {
				throw new BusinessException("The facebook account '" + facebookUsername +  "' doesn't exists");
			}
			
			PostEntity postEntity = new PostEntity();
			postEntity.setDescription(description);
			postEntity.setFacebook(facebookEntity);
			postEntity.setIdPost(idPost);
			postEntity.setLink(link);
			postEntity.setName(name);
			postEntity.setNextPublish(nextPublish);
			postEntity.setPhoto(photo);
			postEntity.setProgress(0);
			
			postQuery.save(postEntity);
			
			for(PostGroupDTO postGroupDTO: postDTO.getGroups()) {
				
				String groupUrl = postGroupDTO.getGroupUrl();
				Integer idPostGroup = postGroupDTO.getIdPostGroup();
				boolean success = false;
				
				PostGroupEntity postGroupEntity = new PostGroupEntity();
				postGroupEntity.setGroupUrl(groupUrl);
				postGroupEntity.setIdPostGroup(idPostGroup);
				postGroupEntity.setPost(postEntity);
				postGroupEntity.setSuccess(success);
				postGroupQuery.save(postGroupEntity);				
			}
			
			tx.commit();
		} catch(Exception ex) {
			
			try {
				tx.rollback();
				throw new RuntimeException(ex);
			} catch (IllegalStateException | SecurityException
					| SystemException e) {
				throw new RuntimeException(e);
			}
		}
		
	}
}
