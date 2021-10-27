package com.homework.rewards.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PurchaseDTO {

	private final String transactionId;
	private final Float totalSpent;
	private final String userId;

	@JsonCreator
	public PurchaseDTO(
			@JsonProperty("transactionId") final String transactionId,
			@JsonProperty("totalSpent") final Float totalSpent,
			@JsonProperty("userId") final String userId) {

		this.transactionId = transactionId;
		this.totalSpent = totalSpent;
		this.userId = userId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public Float getTotalSpent() {
		return totalSpent;
	}

	public String getUserId() {
		return userId;
	}

}
