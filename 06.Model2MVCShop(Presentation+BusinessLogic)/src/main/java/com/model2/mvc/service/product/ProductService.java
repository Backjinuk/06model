package com.model2.mvc.service.product;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

public interface ProductService {
	
	public void insertProduct(Product product) throws Exception;
	
	public Product getProduct(int prodNo) throws Exception;
	
	public Product getProducts(int[] prodNo);

	public int updateProduct(Product product) throws Exception;
	
	public Map<String, Object> getProductList(Search search) throws Exception;

	
}
 