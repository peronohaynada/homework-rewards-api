package com.homework.rewards.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RewardDTO {

	private Integer points;
	private String customerId;
	
	@JsonCreator
	public RewardDTO (@JsonProperty("customerId") final String customerId, @JsonProperty("points") final Integer points) {
		this.customerId = customerId;
		this.points = points;
	}

	public Integer getPoints() {
		return points;
	}

	public void addPoints(Integer points) {
		this.points += points;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	
}
