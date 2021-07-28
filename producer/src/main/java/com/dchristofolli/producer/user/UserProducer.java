package com.dchristofolli.producer.user;

import com.google.gson.Gson;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {
    private final KafkaTemplate<Object, String> kafkaTemplate;
    private final Gson gson = new Gson();

    public UserProducer(KafkaTemplate<Object, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(UserModel user) {
        kafkaTemplate.send("user_topic", gson.toJson(user));
    }
}
