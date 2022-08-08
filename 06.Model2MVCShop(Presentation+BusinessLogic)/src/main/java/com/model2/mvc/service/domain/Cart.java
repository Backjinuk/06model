package com.model2.mvc.service.domain;

public class Cart {

	private Product prodNo;
	
	private User buyerId;
	//	P/K
	private int cartNo;
	// ?ˆ˜?Ÿ‰
	private int cartValnum;
	
	private String cartCode;

	
	public String getCartCode() {
		return cartCode;
	}
	public void setCartCode(String cartCode) {
		this.cartCode = cartCode;
	}
	

	public Product getProdNo() {
		return prodNo;
	}
	public void setProdNo(Product prodNo) {
		this.prodNo = prodNo;
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
	public int getCartValnum() {
		return cartValnum;
	}
	public void setCartValnum(int cartValnum) {
		this.cartValnum = cartValnum;
	}
	@Override
	public String toString() {
		return "CartVO [prodNo=" + prodNo + ", buyerId=" + buyerId + ", cartNo=" + cartNo + ", cartValnum=" + cartValnum
				+ ", cartCode=" + cartCode + "]";
	}


	
	


}
