package com.dchristofolli.consumer.dto;

import com.dchristofolli.consumer.domain.UserEntity;
import com.dchristofolli.consumer.domain.UserEntityBuilder;

public class UserMapper {
    public static UserEntity mapToEntity(UserModel user){
        return new UserEntityBuilder()
            .setName(user.getName())
            .setEmail(user.getEmail())
            .createUserEntity();
    }
}
