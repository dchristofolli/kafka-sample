package com.dchristofolli.kafkasample.consumer;

import com.dchristofolli.kafkasample.consumer.domain.UserEntity;
import com.dchristofolli.kafkasample.consumer.domain.UserRepository;
import com.dchristofolli.kafkasample.consumer.dto.UserModel;
import com.dchristofolli.kafkasample.consumer.dto.UserMapper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;

@Component
public class MessageConsumer {
    private final Logger log = LoggerFactory.getLogger(MessageConsumer.class);
    private final UserRepository repository;
    private final Gson gson = new Gson();

    public MessageConsumer(UserRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "${user.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) Integer key,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp,
                       String message) {
        log.info("key: {}, timestamp: {}, message: {}",
            key, new Timestamp(timestamp).toInstant(), message);
        UserModel userModel = gson.fromJson(message, UserModel.class);
        UserEntity userEntity = UserMapper.mapToEntity(userModel);
        repository.save(userEntity);
    }
}
