package com.dchristofolli.kafkasample.producer;

import com.dchristofolli.kafkasample.consumer.MessageConsumer;
import com.dchristofolli.kafkasample.consumer.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootTest(classes = {
    Controller.class,
    UserService.class,
    MessageProducer.class,
    MessageConsumer.class,
    UserRepository.class
})
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@EnableWebMvc
class ControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    MessageProducer messageProducer;
    @MockBean
    UserRepository userRepository;

    @Test
    void createUser() throws Exception {
        String userJson = "{\"name\":\"daniel\",\"email\":\"daniel@test.com\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson))
            .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}