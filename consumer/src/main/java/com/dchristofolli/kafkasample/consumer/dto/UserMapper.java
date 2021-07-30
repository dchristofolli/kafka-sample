package com.dchristofolli.kafkasample.consumer.dto;

import com.dchristofolli.kafkasample.consumer.domain.UserEntity;
import com.dchristofolli.kafkasample.consumer.domain.UserEntityBuilder;

public class UserMapper {
    public static UserEntity mapToEntity(UserModel user){
        return new UserEntityBuilder()
            .setName(user.getName())
            .setEmail(user.getEmail())
            .createUserEntity();
    }
}
