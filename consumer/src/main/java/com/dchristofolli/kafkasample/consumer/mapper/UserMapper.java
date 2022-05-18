package com.dchristofolli.kafkasample.consumer.mapper;

import com.dchristofolli.kafkasample.consumer.domain.UserEntity;
import com.dchristofolli.kafkasample.consumer.domain.UserEntityBuilder;
import com.dchristofolli.kafkasample.consumer.dto.ConsumerUserModel;

public class UserMapper {
    public static UserEntity mapToEntity(ConsumerUserModel user){
        return new UserEntityBuilder()
            .setName(user.getName())
            .setEmail(user.getEmail())
            .createUserEntity();
    }
}
