package com.dchristofolli.kafkasample.consumer.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "USER")
public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;

    public UserEntity(Long id, String name, String email) {
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
}
