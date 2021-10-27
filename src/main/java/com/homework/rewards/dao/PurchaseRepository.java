package com.homework.rewards.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.homework.rewards.model.Purchase;

public interface PurchaseRepository extends MongoRepository<Purchase, String>{

}
