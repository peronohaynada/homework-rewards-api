package com.homework.rewards;

import java.util.Collection;
import java.util.Collections;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(basePackages = "com.homework.rewards")
public class MongoConfiguration extends AbstractMongoClientConfiguration {

	@Override
    protected String getDatabaseName() {
        return "rewards";
    }
 
    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/rewards");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
        
        return MongoClients.create(mongoClientSettings);
    }
 
    @Override
    public Collection getMappingBasePackages() {
        return Collections.singleton("com.homework.rewards");
    }

}
