package com.model2.mvc.service.purchase;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseDao {

	void addPurchase(Purchase purchase)throws Exception;

	Purchase getPurchase(int tranNo)throws Exception;

	void updatePurchase(Purchase purchase)throws Exception;

	Map<String, Object> getPurchaseList(Search search, String userId) throws Exception;

	int getTotalCount(Search search);

	void updateTranCode(Purchase purchase) throws Exception;

}
