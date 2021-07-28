package com.dchristofolli.consumer.user;

import java.util.Objects;

public class UserModel {
    private String message;
    private String name;
    private String email;

    public UserModel(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public UserModel(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "UserModel{" +
            "name='" + name + '\'' +
            ", email='" + email + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserModel userModel = (UserModel) o;

        if (!Objects.equals(name, userModel.name)) return false;
        return Objects.equals(email, userModel.email);
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
