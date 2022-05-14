package com.dchristofolli.kafkasample.producer;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {
    private final KafkaTemplate<Integer, String> kafkaTemplate;
    private final Logger logger = LoggerFactory.getLogger(MessageProducer.class);
    private final Gson gson = new Gson();

    public MessageProducer(KafkaTemplate<Integer, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(ProducerUserModel user) {
        logger.info("Sending user: {}", user);
        kafkaTemplate.send("user_topic", user.hashCode(), gson.toJson(user));
    }
}
