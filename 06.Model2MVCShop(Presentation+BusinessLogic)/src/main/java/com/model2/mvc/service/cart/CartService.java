package com.model2.mvc.service.cart;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Cart;

public interface CartService {


	public void addCart(Cart cart) throws Exception;
	
	public Cart getCart(Cart cart);
	
	public Map<String, Object> getCartList(Search search, String userId) throws Exception; 
	
	public void deleteCart(int cartNo) throws Exception;
}
