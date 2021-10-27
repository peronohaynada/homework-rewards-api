package com.homework.rewards.bo.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.homework.rewards.bo.PurchaseBO;
import com.homework.rewards.dao.PurchaseRepository;
import com.homework.rewards.dto.PurchaseDTO;
import com.homework.rewards.model.Purchase;

@Component
public class PurchaseBOImpl implements PurchaseBO {
	private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseBOImpl.class);

	@Autowired
	private PurchaseRepository purchaseRepository;

	@Override
	public List<Purchase> getAll() {
		return purchaseRepository.findAll();
	}

	@Override
	public Optional<Purchase> findById(String purchaseId) {
		return purchaseRepository.findById(purchaseId);
	}

	@Override
	public Purchase create(final String userId, final PurchaseDTO purchaseDTO) {
		final Integer points = calculatePoints(purchaseDTO.getTotalSpent());
		LOGGER.info("A total of {} points calculated from the total purchase of {} for the user {}", points,
				purchaseDTO.getTotalSpent(), userId);

		final Purchase purchase = new Purchase(null, purchaseDTO.getTotalSpent(), points, userId);

		return purchaseRepository.save(purchase);
	}

	@Override
	public Optional<Purchase> update(PurchaseDTO purchaseDTO) {
		Optional<Purchase> optional = purchaseRepository.findById(purchaseDTO.getTransactionId());

		if (!optional.isPresent()) {
			return optional;
		}
		else if (!optional.get().getUserId().equals(purchaseDTO.getUserId())) {
			
		}

		final Integer points = calculatePoints(purchaseDTO.getTotalSpent());
		Purchase purchase = new Purchase(optional.get().getTransactionId(), purchaseDTO.getTotalSpent(), points,
				optional.get().getUserId());
		
		return Optional.of(purchaseRepository.save(purchase));
	}

	@Override
	public boolean delete(PurchaseDTO purchaseDTO) {
		if (!purchaseRepository.existsById(purchaseDTO.getTransactionId())) {
			return false;
		}
		purchaseRepository.deleteById(purchaseDTO.getTransactionId());
		return true;
	}

	private Integer calculatePoints(final Float totalSpent) {
		final Integer total = totalSpent.intValue();

		final Integer points = (total > 100) ? 2 * total - 150 : (total > 50) ? total - 50 : 0;
		return points;
	}

}
