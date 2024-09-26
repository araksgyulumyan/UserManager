package com.example.user_manager;

import com.example.TestProducer;
import com.example.user_manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class KafkaProducerService implements CommandLineRunner {

    private final UserRepository userRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value(value = "${kafka.topic.useractivity}")
    private String topicUserActivity;

    public void sendMessage(String key, Object message) {
        kafkaTemplate.send(topicUserActivity, key, message);
    }

    @Override
    public void run(String... args) {
        userRepository.findAll().forEach(each -> {
            sendMessage(String.valueOf(each.getId()), each);
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
