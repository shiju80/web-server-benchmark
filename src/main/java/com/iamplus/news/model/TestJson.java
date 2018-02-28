package com.iamplus.news.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class TestJson{

	@JsonProperty("framework")
	private String framework;

	@JsonProperty("value")
	private String value;

	public void setFramework(String framework){
		this.framework = framework;
	}

	public String getFramework(){
		return framework;
	}

	public void setValue(String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}
}