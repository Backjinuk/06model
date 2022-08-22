package com.model2.mvc.service.cart.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.cart.CartDao;
import com.model2.mvc.service.domain.Cart;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;

@Repository("cartDaoImpl")
public class CartDaoImpl implements CartDao {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public CartDaoImpl() {
		System.out.println(this.getClass());
	}
	
	@Override
	public void addCart(Cart cart) throws Exception {
		sqlSession.insert("CartMapper.addCart", cart);
	}

	@Override
	public Cart getCart(Cart cart) {
		return sqlSession.selectOne("CartMapper.getCart", cart);
	}

	@Override
	public Map<String, Object> getCartList(Search search, String userId) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("search", search);
		map.put("userId", userId);
		
		List<Cart> list = 
					sqlSession.selectList("CartMapper.getCartList" , map);
		
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setBuyerId((User)sqlSession.selectOne("UserMapper.getUser", list.get(i).getBuyerId().getUserId()));
			list.get(i).setProdNo((Product)sqlSession.selectOne("ProductMapper.getProduct", list.get(i).getProdNo().getProdNo()));
		}
		
		map.put("totalCount", sqlSession.selectOne("CartMapper.getTotalCount", map));
		map.put("list", list); 
		
		return map ;
	}

	@Override
	public void deleteCart(int prodNo) throws Exception {
		sqlSession.delete("CartMapper.deleteCart", prodNo);
	}

	@Override
	public int updateCartValue(int prodNo, int cartValue)throws Exception {
		Map<String, Integer> map = 
				new HashMap<String, Integer>(); 
		 
		map.put("prodNo", prodNo);
		map.put("cartValue",cartValue);		
		return sqlSession.update("CartMapper.updateCartValue", map);
		
	}
}
