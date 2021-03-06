package com.dchristofolli.kafkasample;

import com.dchristofolli.kafkasample.consumer.MessageConsumer;
import com.dchristofolli.kafkasample.consumer.domain.UserRepository;
import com.dchristofolli.kafkasample.producer.MessageProducer;
import com.dchristofolli.kafkasample.producer.ProducerUserModel;
import org.junit.ClassRule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import static com.dchristofolli.kafkasample.KafkaTestContainersConfiguration.testcontainersImage;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {MessageProducer.class, MessageConsumer.class})
@Import(KafkaTestContainersConfiguration.class)
@DirtiesContext
class KafkaSampleTest {
    final KafkaTemplate<Integer, String> kafkaTemplate = KafkaTestContainersConfiguration.kafkaTemplate();
    @ClassRule
    static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse(testcontainersImage));
    @Autowired
    MessageProducer producer;
    @MockBean
    UserRepository repository;
    @Mock
    MessageConsumer consumer;

    @BeforeAll
    static void setup() {
        kafka.start();
    }
    @AfterAll
    static void shutdown(){
        kafka.stop();
    }

    @Test
    void containerTest() {
        Assertions.assertTrue(kafka.isRunning());
    }

    @Test
    void send() {
        ProducerUserModel producerUserModel = new ProducerUserModel("daniel", "daniel@test.com");
        String userJson = "{\"name\":\"daniel\",\"email\":\"daniel@test.com\"}";
        Mockito.doNothing().when(consumer).listen(1, 1L, "message");
        producer.send(producerUserModel);
        kafkaTemplate.send("user_topic", userJson);
        consumer.listen(1, 1L, "message");
        Mockito.verify(consumer).listen(1, 1L, "message");
    }
}