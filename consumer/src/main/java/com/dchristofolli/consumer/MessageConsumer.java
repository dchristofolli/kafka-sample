package com.dchristofolli.consumer;

import com.dchristofolli.consumer.domain.UserEntity;
import com.dchristofolli.consumer.domain.UserRepository;
import com.dchristofolli.consumer.dto.UserModel;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import static com.dchristofolli.consumer.dto.UserMapper.mapToEntity;

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
        log.info("key: {}, timestamp: {}, message: {}", key, timestamp, message);
        UserModel userModel = gson.fromJson(message, UserModel.class);
        UserEntity userEntity = mapToEntity(userModel);
        repository.save(userEntity);
    }
}