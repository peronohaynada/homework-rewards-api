package com.homework.rewards.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PurchaseDTO {

	private final String transactionId;
	private final Float totalSpent;
	private final String customerId;
	private final Long timestamp;

	@JsonCreator
	public PurchaseDTO(
			@JsonProperty("transactionId") final String transactionId,
			@JsonProperty("totalSpent") final Float totalSpent,
			@JsonProperty("customerId") final String customerId,
			@JsonProperty("timestamp") final Long timestamp) {

		this.transactionId = transactionId;
		this.totalSpent = totalSpent;
		this.customerId = customerId;
		this.timestamp = timestamp;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public Float getTotalSpent() {
		return totalSpent;
	}

	public String getCustomerId() {
		return customerId;
	}

	public Long getTimestamp() {
		return timestamp;
	}

}
