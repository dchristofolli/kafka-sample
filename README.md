# kafka-sample

### Requirements
- [Java JDK 11](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html)
- [Docker](https://docs.docker.com/get-docker/)
- [IntelliJ](https://www.jetbrains.com/pt-br/idea/download/)

## Running
In order to have Kafka and MongoDB running, we must run: </br>
docker-compose up -d </br>
### Producer Module
Right click and "Run" java/com/dchristofolli/kafkasample/producer/ProducerApplication.java
### Consumer Module
Right click and "Run" java/com/dchristofolli/kafkasample/consumer/ConsumerApplication.java

### Making requests
You can do this using the file kafka-sample-requests.http on this directory root

### Consume Kafka topics
List docker containers -> docker ps | grep confluentinc/cp-kafka:latest </br>
Access kafka container bash -> docker exec -it -w /bin {container id} bash

### [Query Mongo Collection](http://localhost:8088/db/kafka-sample/usersDB)
