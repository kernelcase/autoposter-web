package com.facebook_autoposter.robot.exception;

import java.util.List;

public class ErrorMessageDTO {

private String type;
	
	private String message;
	
	private Integer code;
	
	private List<String> errorData;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public List<String> getErrorData() {
		return errorData;
	}

	public void setErrorData(List<String> errorData) {
		this.errorData = errorData;
	}
}
