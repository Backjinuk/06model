package com.model2.mvc.service.domain;

import java.sql.Date;


public class Comment {
	
	private Product prodNo;
	private User userId;
	private int commentNo;
	private String commentTitle;
	private Date regDate;
	
	
	
	public Product getProdNo() {
		return prodNo;
	}
	public void setProdNo(Product prodNo) {
		this.prodNo = prodNo;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public String getCommentTitle() {
		return commentTitle;
	}
	public void setCommentTitle(String commentTitle) {
		this.commentTitle = commentTitle;
	}
	
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		return "Comment [prodNo=" + prodNo + ", userId=" + userId + ", commentNo=" + commentNo + ", commentTitle="
				+ commentTitle + ", regDate=" + regDate + "]";
	}
	
	
	}	

