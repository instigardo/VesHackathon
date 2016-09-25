package com.hackathon.parser;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.hackathon.core.BaseModel;

@Document(collection = "Response_collector")
public class ResponseCollector extends BaseModel{
	private String title;
	private String author;	
	private Long publishDate;
	private String link;
	private String content;
	private List<String> tags;
	private List<String> headers;
	private List<String> dnbTags;
	private float score;
	private Boolean relevant;
	private Boolean upgrade;
	private Long updateTimeStamp;
	private Boolean enabled;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Long getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Long publishDate) {
		this.publishDate = publishDate;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public List<String> getHeaders() {
		return headers;
	}
	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}
	public List<String> getDnbTags() {
		return dnbTags;
	}
	public void setDnbTags(List<String> dnbTags) {
		this.dnbTags = dnbTags;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public Boolean getRelevant() {
		return relevant;
	}
	public void setRelevant(Boolean relevant) {
		this.relevant = relevant;
	}
	public Boolean getUpgrade() {
		return upgrade;
	}
	public void setUpgrade(Boolean upgrade) {
		this.upgrade = upgrade;
	}
	public Long getUpdateTimeStamp() {
		return updateTimeStamp;
	}
	public void setUpdateTimeStamp(Long updateTimeStamp) {
		this.updateTimeStamp = updateTimeStamp;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	
}
