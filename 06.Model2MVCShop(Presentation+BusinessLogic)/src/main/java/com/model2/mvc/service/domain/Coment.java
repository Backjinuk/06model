package com.model2.mvc.service.domain;

public class Coment {
	
	public int comentNo;  	
    public Product prodNo;
    public User userId;
    public String comentTitle;
	public int getComentNo() {
		return comentNo;
	}
	public void setComentNo(int comentNo) {
		this.comentNo = comentNo;
	}
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
	public String getComentTitle() {
		return comentTitle;
	}
	public void setComentTitle(String comentTitle) {
		this.comentTitle = comentTitle;
	}
	
	@Override
	public String toString() {
		return "Coment [comentNo=" + comentNo + ", prodNo=" + prodNo + ", userId=" + userId + ", comentTitle="
				+ comentTitle + "]";
	}

    
    
    
    

}
