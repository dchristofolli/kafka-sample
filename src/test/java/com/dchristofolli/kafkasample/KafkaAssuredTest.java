package com.dchristofolli.kafkasample;

import com.dchristofolli.kafkasample.consumer.MessageConsumer;
import com.dchristofolli.kafkasample.consumer.domain.UserRepository;
import com.dchristofolli.kafkasample.producer.MessageProducer;
import com.dchristofolli.kafkasample.producer.ProducerUserModel;
import io.sicredi.kafkaassured.KafkaAssured;
import io.sicredi.kafkaassured.Topic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;

import static io.sicredi.kafkaassured.ClientConsumptionConfig.withDifferenceFromOffset;
import static io.sicredi.kafkaassured.impl.KafkaAssuredFactory.kafkaAssured;

@SpringBootTest(classes = {MessageProducer.class, MessageConsumer.class})
class KafkaAssuredTest {
    @Autowired
    private MessageProducer messageProducer;
    @MockBean
    private KafkaTemplate<Integer, String> kafkaTemplate;
    @MockBean
    private UserRepository userRepository;
    private final KafkaAssured kafkaAssured = kafkaAssured("app1wsdigit015d.des.sicredi.net:9092");
    private final Topic<ProducerUserModel> userModelTopic = kafkaAssured.topic("exemplo-kafka-assured-v1",
        ProducerUserModel.class);
    private final ProducerUserModel user = new ProducerUserModel("daniel", "daniel@test.com");

    @Test
    void shouldSendUserToTopic() {
        messageProducer.send(user);
        userModelTopic.verifyUntil(u -> u.equals(user), withDifferenceFromOffset(-5));
    }
}
