package com.springboot.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "article")
public class Article extends BaseBean{
	private String title;
	private String time;
	private int supports;
	@Column(columnDefinition = "text")
	private String content;
	private String owner;
	
	public String getTitle() {
		return title;
	}
	
	public String getTime() {
		return time;
	}
	
	public int getSupports() {
		return supports;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public void setSupports(int supports) {
		this.supports = supports;
	}
	
	public void setContent(String content) {
		this.content =  content;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
}
