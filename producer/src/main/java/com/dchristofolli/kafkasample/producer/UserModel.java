package com.dchristofolli.kafkasample.producer;

public class UserModel {
    private String name;
    private String email;

    public UserModel(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UserModel() {
    }

    @Override
    public String toString() {
        return "UserModel{" +
            "name='" + name + '\'' +
            ", email='" + email + '\'' +
            '}';
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
