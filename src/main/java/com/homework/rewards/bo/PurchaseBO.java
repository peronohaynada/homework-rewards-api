package com.homework.rewards.bo;

import java.util.List;
import java.util.Optional;

import com.homework.rewards.dto.PurchaseDTO;
import com.homework.rewards.model.Purchase;

public interface PurchaseBO {
	
	List<Purchase> getAll();
	
	Optional<Purchase> findById(String purchaseId);
	
	Purchase create(PurchaseDTO purchaseDTO);
	
	Optional<Purchase> update(PurchaseDTO purchaseDTO);
	
	boolean delete(String transactionId);
}
