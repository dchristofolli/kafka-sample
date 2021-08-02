package com.dchristofolli.kafkasample.producer;

import com.google.gson.Gson;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {
    private final KafkaTemplate<Integer, String> kafkaTemplate;
    private final Gson gson = new Gson();

    public MessageProducer(KafkaTemplate<Integer, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(UserModel user) {
        kafkaTemplate.send("user_topic", user.hashCode(), gson.toJson(user));
    }
}
