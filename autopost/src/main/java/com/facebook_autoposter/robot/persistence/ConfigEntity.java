package com.facebook_autoposter.robot.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Config")
public class ConfigEntity {

	@Id
	@Column(name="IdConfig")
	private Integer idConfig;
		
	@Column(name="Key")
	private String key;
	
	@Column(name="Value")
	private String value;

	public Integer getIdConfig() {
		return idConfig;
	}

	public void setIdConfig(Integer idConfig) {
		this.idConfig = idConfig;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
}

