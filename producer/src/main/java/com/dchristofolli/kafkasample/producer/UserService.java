package com.dchristofolli.kafkasample.producer;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final MessageProducer producer;

    public UserService(MessageProducer producer) {
        this.producer = producer;
    }

    public void sendUser(UserModel model) {
        producer.send(model);
    }
}
