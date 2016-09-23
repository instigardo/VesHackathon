package com.hackathon.core;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Api_collector")
public class ApiCollector extends BaseModel{
	private String uri;
	private Long insert_timestamp;
	private Boolean enabled;
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public Long getInsert_timestamp() {
		return insert_timestamp;
	}
	public void setInsert_timestamp(Long insert_timestamp) {
		this.insert_timestamp = insert_timestamp;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}	

}
