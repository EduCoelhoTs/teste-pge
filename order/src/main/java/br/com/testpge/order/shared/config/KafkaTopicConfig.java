package br.com.testpge.order.shared.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import br.com.testpge.order.shared.events.EventsStreamTopcs;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic orderStatusChangeTopicBuilder() {
        return TopicBuilder
                .name(EventsStreamTopcs.ORDER_STATUS_CHANGE)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
