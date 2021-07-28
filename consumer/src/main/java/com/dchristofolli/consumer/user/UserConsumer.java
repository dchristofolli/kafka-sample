package com.dchristofolli.consumer.user;

import com.dchristofolli.consumer.domain.UserEntity;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.dchristofolli.consumer.user.UserMapper.mapToEntity;

@Component
public class UserConsumer {
    private final Logger log = LoggerFactory.getLogger(UserConsumer.class);
    private final UserRepository repository;
    private final Gson gson = new Gson();

    public UserConsumer(UserRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "${user.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(String message) {
        log.info("Message received: {}", message);
        UserModel userModel = gson.fromJson(message, UserModel.class);
        UserEntity userEntity = mapToEntity(userModel);
        repository.save(userEntity);
    }
}
