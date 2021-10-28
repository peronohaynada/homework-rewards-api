package com.homework.rewards.bo;

import java.util.List;

import com.homework.rewards.dto.RewardDTO;

public interface RewardsBO {

	List<RewardDTO> calculateThreeMonthPeriodRewards(final Long date);
	
}
