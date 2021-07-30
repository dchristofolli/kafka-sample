package com.dchristofolli.kafkasample.consumer.domain;

public class UserEntityBuilder {
    private Long id;
    private String name;
    private String email;

    public UserEntityBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public UserEntityBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserEntityBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserEntity createUserEntity() {
        return new UserEntity(id, name, email);
    }
}