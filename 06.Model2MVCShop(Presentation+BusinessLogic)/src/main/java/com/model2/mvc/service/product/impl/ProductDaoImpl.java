package com.model2.mvc.service.product.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;

@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public ProductDaoImpl() {
		System.out.println("::" .getClass() + ":: 진입");
	}
	
	public void insertProduct(Product product) throws Exception{
		System.out.println("product 의 정보 :" + product);
		sqlSession.insert("ProductMapper.insertProduct", product);
	}

	@Override
	public Product getProduct(int product) {
		return sqlSession.selectOne("ProductMapper.getProduct", product);
	}

	@Override
	public int updateProduct(Product product) throws Exception {
		 return sqlSession.update("ProductMapper.updateProduct" , product);
	}
 
	@Override
	public List<Product> getProductList(Search search) throws Exception {
		return sqlSession.selectList("ProductMapper.getList", search);
	}
	@Override
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("ProductMapper.getTotalCount",search);
	}
}
