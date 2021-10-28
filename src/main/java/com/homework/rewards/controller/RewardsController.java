package com.homework.rewards.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.homework.rewards.bo.RewardsBO;
import com.homework.rewards.dto.RewardDTO;

@Controller
@RequestMapping(path = "/rewards")
public class RewardsController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RewardsController.class);
	
	@Autowired
	private RewardsBO rewardsBO;

	@GetMapping(path = "/v1.0/{date}")
	public ResponseEntity<List<RewardDTO>> getRewardsForThreeMonthPeriod(@PathVariable(name = "date") final Long date) {

		if (!isDateValid(date)) {
			LOGGER.error("The date is in the future {}", date);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		final List<RewardDTO> rewards = rewardsBO.calculateThreeMonthPeriodRewards(date);
		
		return new ResponseEntity<List<RewardDTO>>(rewards, HttpStatus.OK);
	}
}
