package com.dchristofolli.kafkasample.producer;

import java.util.Objects;

public class ProducerUserModel {
    private String name;
    private String email;

    public ProducerUserModel(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public ProducerUserModel() {
    }

    @Override
    public String toString() {
        return "ProducerUserModel{" +
            "name='" + name + '\'' +
            ", email='" + email + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProducerUserModel producerUserModel = (ProducerUserModel) o;

        if (!Objects.equals(name, producerUserModel.name)) return false;
        return Objects.equals(email, producerUserModel.email);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
