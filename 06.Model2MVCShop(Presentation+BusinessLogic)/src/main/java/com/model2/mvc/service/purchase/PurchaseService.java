package com.model2.mvc.service.purchase;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;

public interface PurchaseService {
	
	public void addPurchase(Purchase purchase) throws Exception;
	
	public Purchase getPurchase(int tranNo)throws Exception;
	
	public void updatePurchase(Purchase purchase)throws Exception;
	
	public Map<String, Object> getPurchaseList(Search search, String string )throws Exception;

	public void updateTranCode(Purchase purchase)throws Exception;

}
 