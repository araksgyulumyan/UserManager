package com.example.user_manager.config;
//todo change package name

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${kafka.bootstrap.address}")
    private String bootstrapAddress;

    @Value(value = "${kafka.topic.useractivity}")
    private String topicUserActivity;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put("bootstrap.servers", bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic createUserActivityTopic() {
        return new NewTopic(topicUserActivity, 2, (short) 1);
    }
}
