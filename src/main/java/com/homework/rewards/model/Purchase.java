package com.homework.rewards.model;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.annotation.Id;

public class Purchase {

	@Id
	private final String transactionId;
	private final Float totalSpent;
	private final Integer generatedPoints;
	private final String customerId;
	private final Long timestamp;

	@BsonCreator
	public Purchase(@BsonProperty("transactionId") final String transactionId,
			@BsonProperty("totalSpent") final Float totalSpent,
			@BsonProperty("generatedPoints") final Integer generatedPoints,
			@BsonProperty("customerId") final String customerId,
			@BsonProperty("timestamp") final Long timestamp) {

		this.transactionId = transactionId;
		this.totalSpent = totalSpent;
		this.generatedPoints = generatedPoints;
		this.customerId = customerId;
		this.timestamp = timestamp;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public Float getTotalSpent() {
		return totalSpent;
	}

	public Integer getGeneratedPoints() {
		return generatedPoints;
	}

	public String getCustomerId() {
		return customerId;
	}

	public Long getTimestamp() {
		return timestamp;
	}

}
