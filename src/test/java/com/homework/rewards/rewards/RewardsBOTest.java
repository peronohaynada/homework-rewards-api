package com.homework.rewards.rewards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.homework.rewards.bo.impl.RewardsBOImpl;
import com.homework.rewards.dto.RewardDTO;
import com.homework.rewards.model.Purchase;

public class RewardsBOTest {

	@Mock
	private MongoTemplate mongoTemplate;
	
	@InjectMocks
	private RewardsBOImpl rewardsBO;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void calculateThreeMonthPeriodRewardsTest() {
		List<Object> mockList = new ArrayList<>();
		mockList.add(new Purchase("123", 120.0F, 90, "bca321", 1632883174000L));
		mockList.add(new Purchase("456", 160.0F, 170, "bca321", 1632883174001L));
		mockList.add(new Purchase("789", 120.0F, 90, "fed654", 1632883174002L));
		when(mongoTemplate.find(Matchers.any(Query.class), Matchers.any())).thenReturn(mockList);
		
		List<RewardDTO> result = rewardsBO.calculateThreeMonthPeriodRewards(1632883188001L);
		
		verify(mongoTemplate, times(1)).find(Matchers.any(Query.class), Matchers.any());
		assertEquals(2, result.size());
		assertEquals(260, result.get(0).getPoints());
		assertEquals(90, result.get(1).getPoints());
	}
	
	@AfterEach
	public void endTests() throws Exception {
		MockitoAnnotations.openMocks(this).close();
	}
}
