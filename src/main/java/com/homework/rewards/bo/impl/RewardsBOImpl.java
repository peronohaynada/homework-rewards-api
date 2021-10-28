package com.homework.rewards.bo.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.homework.rewards.bo.RewardsBO;
import com.homework.rewards.dto.RewardDTO;
import com.homework.rewards.model.Purchase;

@Component
public class RewardsBOImpl implements RewardsBO {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public List<RewardDTO> calculateThreeMonthPeriodRewards(Long date) {

		final Long period = extractThreeMonthsToDate(date);

		final List<Purchase> purchases = findPurchasesInPeriod(date, period);

		final Map<String, RewardDTO> rewards = processRewards(purchases);

		return rewards.values().stream().collect(Collectors.toList());

	}

	private List<Purchase> findPurchasesInPeriod(Long date, final Long period) {
		Query query = new Query();
		query.addCriteria(Criteria.where("timestamp").lt(date).gt(period));
		List<Purchase> purchases = mongoTemplate.find(query, Purchase.class);
		return purchases;
	}

	private Map<String, RewardDTO> processRewards(List<Purchase> purchases) {
		Map<String, RewardDTO> rewards = new HashMap<>();

		for (final Purchase purchase : purchases) {
			if (!rewards.containsKey(purchase.getCustomerId())) {
				RewardDTO reward = new RewardDTO(purchase.getCustomerId(), purchase.getGeneratedPoints());
				rewards.put(purchase.getCustomerId(), reward);
			} else {
				rewards.get(purchase.getCustomerId()).addPoints(purchase.getGeneratedPoints());
			}
		}
		
		return rewards;
	}

	private Long extractThreeMonthsToDate(Long date) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		calendar.add(Calendar.MONTH, -3);
		Long period = calendar.getTimeInMillis();
		
		return period;
	}

}
