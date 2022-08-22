package com.model2.mvc.service.cart.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.cart.CartDao;
import com.model2.mvc.service.cart.CartService;
import com.model2.mvc.service.domain.Cart;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;

@Service("cartServiceImpl")
public class CartServiceImpl implements CartService {
	
	@Autowired
	@Qualifier("cartDaoImpl")
	private CartDao cartDao;

	@Override
	public void addCart(Cart cart) throws Exception {
		cartDao.addCart(cart);
	}

	@Override
	public Cart getCart(Cart cart) {
		return cartDao.getCart(cart);
	}

	@Override
	public Map<String, Object> getCartList(Search search, String userId) throws Exception {
		return cartDao.getCartList(search, userId);
	}


	@Override
	public void deleteCart(int prodNo) throws Exception {
		cartDao.deleteCart(prodNo);
	}

	@Override
	public int updateCartValue(int prodNo, int cartValue) throws Exception {
		return cartDao.updateCartValue(prodNo, cartValue);
	}

}
