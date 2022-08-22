package com.model2.mvc.service.domain;


import java.util.Date;

public class Product {
	
	private int prodNo;
	private String fileName;
	private String manuDate;
	private int price;
	private String prodDetail;
	private String prodName;
	private Date regDate;
	// 諛곗넚 �긽�깭
	private String proTranCode = "판매중";
	// 총 수량
	private int productValue;
	//구매할 수량
	private int productValNum;
	public Product(){
	}
	
	public String getProTranCode() {
		return proTranCode;
	}
	public void setProTranCode(String proTranCode) {
		this.proTranCode = proTranCode;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getManuDate() {
		return manuDate;
	}
	public void setManuDate(String manuDate) {
		this.manuDate = manuDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getProdDetail() {
		return prodDetail;
	}
	public void setProdDetail(String prodDetail) {
		this.prodDetail = prodDetail;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getProdNo() {
		return prodNo;
	}
	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date  date) {
		this.regDate = date;
	}
	

	public int getProductValNum() {
		return productValNum;
	}

	public void setProductValNum(int productValNum) {
		this.productValNum = productValNum;
	}

	public int getProductValue() {
		return productValue;
	}

	public void setProductValue(int productValue) {
		this.productValue = productValue;
	}

	@Override
	public String toString() {
		return "Product [prodNo=" + prodNo + ", fileName=" + fileName + ", manuDate=" + manuDate + ", price=" + price
				+ ", prodDetail=" + prodDetail + ", prodName=" + prodName + ", regDate=" + regDate + ", proTranCode="
				+ proTranCode + ", productValue=" + productValue + ", productValNum=" + productValNum + "]";
	}

	
	
}