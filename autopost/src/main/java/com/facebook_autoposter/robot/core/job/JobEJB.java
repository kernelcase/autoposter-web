package com.facebook_autoposter.robot.core.job;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.transaction.UserTransaction;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.facebook_autoposter.robot.core.config.ConfigEJB;
import com.facebook_autoposter.robot.core.config.ConfigKey;
import com.facebook_autoposter.robot.core.file.FileEJB;
import com.facebook_autoposter.robot.exception.BusinessException;
import com.facebook_autoposter.robot.persistence.ConfigEntity;
import com.facebook_autoposter.robot.persistence.ConfigQuery;
import com.facebook_autoposter.robot.persistence.JobEntity;
import com.facebook_autoposter.robot.persistence.JobGroupEntity;
import com.facebook_autoposter.robot.persistence.JogGroupQuery;
import com.facebook_autoposter.robot.persistence.JobQuery;

@Stateless
@TransactionManagement( TransactionManagementType.BEAN)
public class JobEJB {

	@Resource
	private EJBContext context;
	
	@Inject
	private JobQuery jobQuery;
	
	@Inject
	private JogGroupQuery jobGroupQuery;
	
	@Inject
	private ConfigQuery configQuery;
	
	@Inject
	private FileEJB fileEJB;
	
	@Inject
	private ConfigEJB configEJB;
	
	@Asynchronous
	public void createJob(JobDTO jobDTO) {
		
		if(jobDTO == null) {
			return;
		}
		UserTransaction utx = context.getUserTransaction();
		
		Integer maxJobs = null;
		Integer currentJobs = null;
		/*
		try {
			
			utx.begin();
			 
			currentJobs = Integer.parseInt(configEJB.getValue(ConfigKey.CURRENT_JOBS));
			maxJobs = Integer.parseInt(configEJB.getValue(ConfigKey.MAX_JOBS));
			 
			utx.commit();
		 
		}
		catch(Exception ex) {		
			try {
				utx.rollback();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		// Do nothing if the server it's busy
		if(currentJobs >= maxJobs) {
			return;
		}
		*/
		
		String description = jobDTO.getDescription();
		String facebookPassword = jobDTO.getFacebookPassword();
		String facebookUsername = jobDTO.getFacebookUsername();
		Integer idPost = jobDTO.getIdPost();
		String link = jobDTO.getLink();
		String name = jobDTO.getName();
		String photo = jobDTO.getPhoto();
		
		List<JobGroupDTO> groups = jobDTO.getGroups();
		
		JobEntity jobEntity = new JobEntity();
		jobEntity.setDescription(description);
		jobEntity.setFacebookPassword(facebookPassword);
		jobEntity.setFacebookUsername(facebookUsername);
		jobEntity.setIdPost(idPost);
		jobEntity.setLink(link);
		jobEntity.setName(name);
		jobEntity.setPhoto(photo);
		jobEntity.setIdJobStatus(IdJobStatus.START);
		
		List<JobGroupEntity> jobGroupEntityList = new ArrayList<JobGroupEntity>();
		for(JobGroupDTO jobGroupDTO: groups) {
			JobGroupEntity jobGroupEntity = new JobGroupEntity();
			jobGroupEntity.setIdJobGroup(jobGroupDTO.getIdJobGroup());
			jobGroupEntity.setJob(jobEntity);
			jobGroupEntity.setGroupUrl(jobGroupDTO.getGroupUrl());
			jobGroupEntity.setSuccess(false);
			jobGroupEntityList.add(jobGroupEntity);
		}			
		
		try {
			utx.begin();
			 
			jobQuery.save(jobEntity);
			for(JobGroupEntity jobGroupEntity: jobGroupEntityList) {
				jobGroupQuery.save(jobGroupEntity);
			}
			 
			utx.commit();
		 
		} catch(Exception ex) {
			try {
				utx.rollback();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		 
		String filename = RandomStringUtils.randomAlphabetic(6) + ".png";
		String photoPath = fileEJB.tmpFilePath(filename);
			
		BufferedImage image = null;
	    try {
	        
	    	URL url = new URL(photo);
	        image = ImageIO.read(url);                
	        ImageIO.write(image, "png",new File(photoPath));
	            
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
			
		WebDriver driver = new ChromeDriver();
			
		driver.get("https://www.facebook.com");
		driver.findElement(By.id("email")).sendKeys(facebookUsername);
		driver.findElement(By.id("pass")).sendKeys(facebookPassword);
		driver.findElement(By.id("loginbutton")).click();
			
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}				
		
		// Start publish in each group
		int progress = 0; 
		int inc = 100/groups.size();		
		for(JobGroupDTO jobGroupDTO: groups) {		
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);			
			driver.navigate().to(jobGroupDTO.getGroupUrl());
				
			try {
				Alert alert = driver.switchTo().alert();
				alert.accept();
			} catch(Exception ex) {
					
		    }
			
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			List<WebElement> searchSellIcon = driver.findElements(By.className("sp_E1vCkfNp0Tk"));
			boolean isPresent = searchSellIcon.size() > 0;
			if(isPresent) {
				// Search the conversation icon
				List<WebElement> searchTalkIcon = driver.findElements(By.className("sp_u4KX0L2wm3m"));
				WebElement talkIcon = null;
				for(WebElement searchIcon: searchTalkIcon) {
					String attrValue = searchIcon.getAttribute("data-reactid");
					if(attrValue != null) {
						talkIcon = searchIcon;
					}
				}
				
				if(talkIcon != null) {
					talkIcon.click();
				} else {
					throw new BusinessException("The talk icon doesn't be found");
				}				
			}
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
			WebElement textarea = driver.findElement(By.tagName("textarea"));
			textarea.click();
			textarea.sendKeys(description);	
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			// Buscar el elemento donde se cargan las fotos			
			WebElement uploadPhoto = driver.findElement(By.cssSelector("input[name='composer_photo']"));
			uploadPhoto.sendKeys(photoPath);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			WebElement publishButton = driver.findElement(By.xpath("//button[@data-testid='react-composer-post-button' and not(@disabled)]"));			
			publishButton.click();
			
			// Update the progress for the group
			progress += inc;
			jobEntity.setProgress(progress);			
			try {
				
				utx.begin();
				 			
				jobQuery.update(jobEntity);
				 
				utx.commit();
			 
			}
			catch(Exception ex) {		
				try {
					utx.rollback();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		
		// Update the 100 progress of the job
		// Discount the current jobs by one
		progress = 100;
		jobEntity.setProgress(progress);
		jobEntity.setIdJobStatus(IdJobStatus.FINISH);		
		currentJobs--;
				
		try {
			
			utx.begin();
			
			ConfigEntity configEntity = configQuery.getByKey(ConfigKey.CURRENT_JOBS);
			configEntity.setValue(currentJobs.toString());			
			configQuery.save(configEntity);			
			jobQuery.update(jobEntity);
			 
			utx.commit();
		 
		}
		catch(Exception ex) {		
			try {
				utx.rollback();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}			
		
	}
	
	public JobDTO getJob(Integer idJob) {
		
		UserTransaction utx = context.getUserTransaction();
		JobDTO jobDTO = null;
		try {
			
			jobDTO = new JobDTO();
			
			utx.begin();
			
			List<JobGroupEntity> jobGroupEntityList = jobGroupQuery.getJobGroups(idJob);
			List<JobGroupDTO> jobGroupDTOList = new ArrayList<JobGroupDTO>();
			for(JobGroupEntity jobGroupEntity: jobGroupEntityList) {
				JobGroupDTO jobGroupDTO = new JobGroupDTO();
				jobGroupDTO.setGroupUrl(jobGroupEntity.getGroupUrl());
				jobGroupDTO.setIdJobGroup(jobGroupEntity.getIdJobGroup());
				jobGroupDTO.setSuccess(jobGroupEntity.getSuccess());
				jobGroupDTOList.add(jobGroupDTO);
			}
			
			JobEntity jobEntity = jobQuery.getById(idJob);
			jobDTO.setDescription(jobEntity.getDescription());
			jobDTO.setFacebookPassword(jobEntity.getFacebookUsername());
			jobDTO.setFacebookUsername(jobEntity.getFacebookUsername());
			jobDTO.setGroups(jobGroupDTOList);
			jobDTO.setIdPost(jobEntity.getIdPost());
			jobDTO.setIdJobStatus(jobEntity.getIdJobStatus());
			jobDTO.setProgress(jobEntity.getProgress());
			jobDTO.setLink(jobEntity.getLink());
			jobDTO.setName(jobEntity.getName());
			jobDTO.setPhoto(jobEntity.getPhoto());
			
			utx.commit();
		 
		}
		catch(Exception ex) {		
			try {
				utx.rollback();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		return jobDTO;
	}
	
}
