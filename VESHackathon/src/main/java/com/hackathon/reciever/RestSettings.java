package com.hackathon.reciever;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "lead")
public class RestSettings {
	String cron;
	List<String> restUris;

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public List<String> getRestUris() {
		return restUris;
	}

	public void setRestUris(List<String> restUris) {
		this.restUris = restUris;
	}
		
}
