package com.facebook_autoposter.robot.exception;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class Validation {
	
	private List<String> params = new ArrayList<String>();
	
	private List<Object> values = new ArrayList<Object>();
	
	private static final int REQUIRED = 1;
	
	private static final int URL = 2;
	
	protected int mode = 0;
	
	public static Validation required() {
		Validation errorValidator = new Validation();		
		errorValidator.setMode(REQUIRED);		
		return errorValidator;
	}
	
	public static Validation url() {
		Validation errorValidator = new Validation();		
		errorValidator.setMode(URL);		
		return errorValidator;
	}
	
	protected void setMode(int mode) {
		this.mode = mode;
	}
	
	private void validateRequired() {
		ErrorMessageDTO errorDTO = new ErrorMessageDTO();
		List<String> errorData = new ArrayList<String>();
		
		for(int i = 0; i < params.size(); i++) {
			String param = params.get(i);
			Object value = values.get(i);
			
			boolean hasError = false;
			if(value == null) {
				hasError = true;
			} else {
				if(StringUtils.isEmpty(value.toString())) {
					hasError = true;
				}
			}
			
			if(hasError) {
				errorData.add(param);
			}
		}
		
		if(errorData.size() > 0) {
			errorDTO.setErrorData(errorData);
			errorDTO.setCode(100);
			errorDTO.setType("Validation");
			errorDTO.setMessage("Required data");
			BusinessException businessException = new BusinessException();
			businessException.setErrorMessage(errorDTO);
			throw businessException;
		}
	}
	
	private void validateUrl() {
		ErrorMessageDTO errorDTO = new ErrorMessageDTO();
		List<String> errorData = new ArrayList<String>();
		
		
		for(int i = 0; i < params.size(); i++) {
			String param = params.get(i);
			Object value = values.get(i);
			
			boolean hasError = false;
			
			URL u = null;
			try {
				u = new URL((String) value);
			} catch (MalformedURLException e1) {
				hasError = true;
			} 
			try {
				u.toURI();
			} catch (URISyntaxException e) {
				hasError = true;
			}
			
			if(hasError) {
				errorData.add(param);
			}
		}
		
		if(errorData.size() > 0) {
			errorDTO.setErrorData(errorData);
			errorDTO.setCode(100);
			errorDTO.setType("Validation");
			errorDTO.setMessage("Malformed URL");
			BusinessException businessException = new BusinessException();
			businessException.setErrorMessage(errorDTO);
			throw businessException;
		}
		
	}
	
	public Validation setParam(String param, Object value) {
		
		params.add(param);
		values.add(value);
		
		return this;
	}
	
	public void validate() {
		
		if(mode == REQUIRED) {
			validateRequired();
		} else if(mode == URL) {
			validateUrl();
		}
		
	}	
}
