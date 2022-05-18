package com.dchristofolli.kafkasample;

import com.dchristofolli.kafkasample.consumer.MessageConsumer;
import com.dchristofolli.kafkasample.consumer.domain.UserRepository;
import com.dchristofolli.kafkasample.consumer.dto.ConsumerUserModel;
import com.dchristofolli.kafkasample.producer.MessageProducer;
import com.dchristofolli.kafkasample.producer.ProducerUserModel;
import com.google.gson.Gson;
import io.sicredi.kafkaassured.KafkaAssured;
import io.sicredi.kafkaassured.Topic;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import static io.sicredi.kafkaassured.ClientConsumptionConfig.withDifferenceFromOffset;
import static io.sicredi.kafkaassured.impl.KafkaAssuredFactory.kafkaAssured;

@SpringBootTest(classes = {MessageProducer.class, MessageConsumer.class})
class ConsumerKafkaAssuredTest {
    @Autowired
    private MessageConsumer messageConsumer;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private KafkaTemplate<Integer, String> kafkaTemplate;
    private Gson gson;
    private final KafkaAssured kafkaAssured = kafkaAssured("app1wsdigit015d.des.sicredi.net:9092");
    private final Topic<ConsumerUserModel> userModelTopic = kafkaAssured.topic("exemplo-kafka-assured-v1",
        ConsumerUserModel.class);
    private final ConsumerUserModel user = new ConsumerUserModel("daniel", "daniel@test.com");

    @Test
    void shouldReceiveUserFromTopic() {
        userModelTopic.send(user);
        Assertions.assertEquals(user, listen("message"));
    }

    @KafkaListener(topics = "exemplo-kafka-assured-v1")
    ConsumerUserModel listen(String message) {
        return gson.fromJson(message, ConsumerUserModel.class);
    }
}
