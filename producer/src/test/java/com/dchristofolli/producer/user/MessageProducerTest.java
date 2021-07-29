package com.dchristofolli.producer.user;

import com.dchristofolli.producer.MessageProducer;
import com.dchristofolli.producer.UserModel;
import org.junit.ClassRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
class MessageProducerTest {
    @Autowired
    private KafkaTemplate<Object, String> kafkaTemplate;
    @ClassRule
    public static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));
    @Autowired
    MessageProducer producer;

    @BeforeAll
    static void setup() {
        kafka.start();
    }

    @Test
    void containerTest() {
        Assertions.assertTrue(kafka.isRunning());
    }

    @Test
    void send() {
        UserModel userModel = new UserModel("daniel", "daniel@ilia.digital");
        String userJson = "{\"name\":\"daniel\",\"email\":\"daniel@ilia.digital\"}";
        producer.send(userModel);
        kafkaTemplate.send("user_topic", userJson);
    }
}