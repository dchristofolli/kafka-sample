package com.dchristofolli.kafkasample.consumer.dto;

import java.util.Objects;

public class ConsumerUserModel {
    private final String name;
    private final String email;

    public ConsumerUserModel(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "ConsumerUserModel{" +
            "name='" + name + '\'' +
            ", email='" + email + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConsumerUserModel consumerUserModel = (ConsumerUserModel) o;

        if (!Objects.equals(name, consumerUserModel.name)) return false;
        return Objects.equals(email, consumerUserModel.email);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
