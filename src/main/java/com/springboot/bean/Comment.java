package com.springboot.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment extends BaseBean{
	private long articleId;
	private String account;
	private String content;
	private long onComment;
	private String replyAccount;
	
	public long getArticleId() {
		return articleId;
	}
	
	public String getAccount() {
		return account;
	}
	
	public String getContent() {
		return content;
	}
	
	public long getOnComment() {
		return onComment;
	}
	
	public String getReplyAccount() {
		return replyAccount;
	}
	
	
	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}
	
	public void setAccount(String account) {
		this.account = account;
	} 
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setOnComment(long onComment) {
		this.onComment = onComment;
	}
	
	public void setReplyId(String replyAccount) {
		this.replyAccount = replyAccount;
	}
	
}
