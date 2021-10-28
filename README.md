# Rewards API

## Introduction
This is a Spring boot API that connects to a MongoDB and its purpose is to calculate the total points rewarded to the customers during a 3 month period.

The rules applied to calculate the points are as follows:
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points)

It contains two API
* /purchase (currently under v1.0) for all CRUD operations currently and get all and get for a specific transaction
* /rewards (currently under v1.0) with only one endpoint that accepts a date and returns all points gained by customers during past 3 months.

## Deployment
(pre requisites) 
Have JDK 8, Maven 3 and GIT installed in your local machine.
You need MongoDB as well. You can use the free tier that atlas has available in the cloud or install it your local. Either solution may require to update the
``` 
src/main/resources/application.properties
```
to allow the application to connect to your MongoDB.

Clone the project.
Run the console and navigate to the folder where the project is cloned and run this command: 
```
mvn clean install
``` 
This will package the project in a jar file located in the target folder named *rewards-0.0.1-SNAPSHOT.jar*
In the console move to the target directory and run this command: 
```
java -jar rewards-0.0.1-SNAPSHOT.jar
```

## Testing
In the repository you will find under the src/test folder you will find two files:
* rewardsMongo.json which contains a small collection for you to import in your MongoDB in a database named rewards
* Reward.postman_collection.json which contains a postman collection.
  

