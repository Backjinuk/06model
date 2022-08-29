package com.model2.mvc.service.domain;

import java.sql.Date;

public class ReComment {
	
	private int recommentNo;
	private User userId;
	private Comment commentNo;
	private String recommentTitle;
	private Date regDate;
	private Product prodNo;
	
	public int getRecommentNo() {
		return recommentNo;
	}
	public void setRecommentNo(int recommentNo) {
		this.recommentNo = recommentNo;
	}
	
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public Comment getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(Comment commentNo) {
		this.commentNo = commentNo;
	}
	public String getRecommentTitle() {
		return recommentTitle;
	}
	public void setRecommentTitle(String recommentTitle) {
		this.recommentTitle = recommentTitle;
	}
	
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	
	
	public Product getProdNo() {
		return prodNo;
	}
	public void setProdNo(Product prodNo) {
		this.prodNo = prodNo;
	}
	@Override
	public String toString() {
		return "ReComment [recommentNo=" + recommentNo + ", userId=" + userId + ", commentNo=" + commentNo
				+ ", recommentTitle=" + recommentTitle + ", regDate=" + regDate + ", prodNo=" + prodNo + "]";
	}
	
	
	
	
	
	
	

}
