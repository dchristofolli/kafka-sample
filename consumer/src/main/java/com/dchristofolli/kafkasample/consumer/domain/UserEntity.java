package com.dchristofolli.kafkasample.consumer.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "usersDB")
public class UserEntity {
    @MongoId
    private String id;
    private String name;
    private String email;

    public UserEntity(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UserEntity() {

    }

    @Override
    public String toString() {
        return "UserEntity{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            '}';
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
