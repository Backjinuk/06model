package com.model2.mvc.service.domain;

public class Cart {

	private Product prodNo;
	
	private User buyerId;
	//	P/K
	private int cartNo;
	// ?ˆ˜?Ÿ‰	
	private String cartCode;
	
	private String tranCartCode;
	
	private int cartValue;
	
	public String getCartCode() {
		return cartCode;
	}
	public void setCartCode(String cartCode) {
		this.cartCode = cartCode;
	}
	

	public Product getProdNo() {
		return prodNo;
	} 
	public Product setProdNo(Product prodNo) { 
		return this.prodNo = prodNo;
	}
	public User getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(User buyerId) {
		this.buyerId = buyerId;
	}
	public int getCartNo() {
		return cartNo;
	}
	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}
	public String getTranCartCode() {
		return tranCartCode;
	}
	public void setTranCartCode(String tranCartCode) {
		this.tranCartCode = tranCartCode;
	}
	
	public int getCartValue() {
		return cartValue;
	}
	public void setCartValue(int cartValue) {
		this.cartValue = cartValue;
	}
	@Override
	public String toString() {
		return "Cart [prodNo=" + prodNo + ", buyerId=" + buyerId + ", cartNo=" + cartNo + ", cartCode=" + cartCode
				+ ", tranCartCode=" + tranCartCode + ", cartValue=" + cartValue + "]";
	}
	



	
	


}
