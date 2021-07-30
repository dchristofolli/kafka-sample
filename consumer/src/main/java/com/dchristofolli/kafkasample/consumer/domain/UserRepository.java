package com.dchristofolli.kafkasample.consumer.domain;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value = "repository")
@Primary
public interface UserRepository extends JpaRepository<UserEntity, String> {
}
