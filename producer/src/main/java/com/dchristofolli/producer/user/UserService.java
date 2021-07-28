package com.dchristofolli.producer.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserProducer producer;

    public UserService(UserProducer producer) {
        this.producer = producer;
    }

    public void sendUser(UserModel model) {
        producer.send(model);
    }
}
