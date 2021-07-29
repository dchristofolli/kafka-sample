package com.dchristofolli.kafkasample;

import com.dchristofolli.consumer.MessageConsumer;
import com.dchristofolli.consumer.domain.UserRepository;
import com.dchristofolli.consumer.domain.UserRepositoryImpl;
import com.dchristofolli.producer.MessageProducer;
import com.dchristofolli.producer.UserModel;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.junit.ClassRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {MessageProducer.class, MessageConsumer.class})
@Import(KafkaSampleTest.KafkaTestContainersConfiguration.class)
@DirtiesContext
class KafkaSampleTest {
    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;
    @ClassRule
    public static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));
    @Autowired
    MessageProducer producer;
    @Mock
    MessageConsumer consumer;

    @BeforeAll
    static void setup() {
        kafka.start();
    }

    @Test
    void containerTest() {
        Assertions.assertTrue(kafka.isRunning());
    }

    @Test
    void send() {
        UserModel userModel = new UserModel("daniel", "daniel@ilia.digital");
        String userJson = "{\"name\":\"daniel\",\"email\":\"daniel@ilia.digital\"}";
        Mockito.doNothing().when(consumer).listen("message");
        producer.send(userModel);
        kafkaTemplate.send("user_topic", userJson);
        consumer.listen("message");
        Mockito.verify(consumer, Mockito.times(1)).listen("message");
    }

    @TestConfiguration
    static class KafkaTestContainersConfiguration {
        @Bean
        ConcurrentKafkaListenerContainerFactory<Integer, String> kafkaListenerContainerFactory() {
            ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
            factory.setConsumerFactory(consumerFactory());
            return factory;
        }

        @Bean
        public ConsumerFactory<Integer, String> consumerFactory() {
            return new DefaultKafkaConsumerFactory<>(consumerConfigs());
        }

        @Bean
        public Map<String, Object> consumerConfigs() {
            Map<String, Object> props = new HashMap<>();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaSampleTest.kafka.getBootstrapServers());
            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
            props.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
            return props;
        }

        @Bean
        public ProducerFactory<Integer, String> producerFactory() {
            Map<String, Object> configProps = new HashMap<>();
            configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaSampleTest.kafka.getBootstrapServers());
            configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
            configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
            return new DefaultKafkaProducerFactory<>(configProps);
        }

        @Bean
        public KafkaTemplate<Integer, String> kafkaTemplate() {
            return new KafkaTemplate<>(producerFactory());
        }

        @Bean
        public UserRepository userRepository() {
            return new UserRepositoryImpl();
        }
    }
}