# NUWE Hack - Java Backend - Auction System

A long-established auction house wants to modernise and move away from conventional methods. Facing an increasingly digital world, they have challenged us to create an auction system as exciting and dynamic as their face-to-face events. Your mission is to use your Java skills to complete the backend of this traditional place so that it can adapt to the new times and attract a new generation of users.

### Stack proposed

- Java 21
- Spring Framework
- Spring Boot
- Spring Web
- Maven

### Difficulty

The difficulty of the challenge depends on the experience you have working with these technologies.
The average difficulty is: easy.

### Objectives

1. Implementation of the "**getWinningBidder**" method in the **HackathonService** class:
    - Complete the body of the getWinningBidders() method. This method must return a Map of the Items that have been bid on (that there is a Bidder) and whose value is the name of the Bidder that has bid.
2. Implementation of the "**Art**" class:
    - Include the Art class as a child of the Item class.
    - Include the implementation of the constructor.
    - Include the getter and setter methods.
3. Implementation of the "**makeOffer**" method:
    - The body of the makeOffer() method must be completed, which upon receiving the name of the item(itemName), the bid amount(amount), and the bidder making the bid(bidder).
    - It must check if the item specified by itemName exists in the list of items:
        - If the item is not found, returns the constant ITEM_NOT_FOUND.
    - If the item is found, compares the bid (amount) with the current highest bid for the item (highestOffer).
        - If the bid is higher than the highest bid, it updates the highest bid and the current bidder for the item and returns the constant OFFER_ACCEPTED.
        - If the bid is equal to or lower than the highest bid, it returns the constant OFFER_REJECTED.  
4. Implementation of the "**getItemsByType**" method in the **HackathonService** class:
    - The body of the getItemsByType() method that receives the type parameter of type String must be completed.
    - This method must return a list of all the items available in the auction filtered by the type it receives.

Remember that the list of items is already injected into the service from the MockDataConfig.java file.

### Testing

In order to test locally, the following commands must be executed:
```bash
mvn clean install
java -jar target/Hackathon-Java.jar
mvn test
```

# Repository structure

A repository tree is provided below and should not be modified. Everything you need to develop the challenge is already included.
```bash
nuwehack-java-auctions/
├── mvnw*
├── mvnw.cmd*
├── pom.xml*
├── README.md
├── src
   ├── main
   │   ├── java
   │   │   └── com
   │   │       └── hackathon
   │   │           ├── hackathon
   │   │           │   ├── config
   │   │           │   │   └── MockDataConfig.java*
   │   │           │   ├── controller
   │   │           │   │   └── HackathonController.java
   │   │           │   ├── HackathonApplication.java
   │   │           │   ├── model
   │   │           │   │   ├── Art.java
   │   │           │   │   ├── Bidder.java
   │   │           │   │   ├── Book.java
   │   │           │   │   ├── Item.java
   │   │           │   │   └── Offer.java
   │   │           │   └── service
   │   │           │       └── HackathonService.java
   │   │           ├── JsonEvent.java*
   │   │           ├── JsonOutListener.java*
   │   │           └── TestResult.java*
   │   └── resources
   │       └── application.properties*
   └── test
       └── java
           └── com
               └── hackathon
                   └── hackathon
                       └── HackathonControllerTest.java*
```
The files marked with * must not be modified, as they are part of what is provided for the correct functioning of the tests and the project in general.

**It is necessary to modify only the files proposed in the objectives**.

## Scoring
The final score will be given according to whether or not the objectives have been met.

In this case, the challenge objectives will be evaluated on 900 points which are distributed as follows:

- Objective 1: 180 points
- Objective 2: 45 points
- Objective 3: 450 points
- Objective 4: 225 points


