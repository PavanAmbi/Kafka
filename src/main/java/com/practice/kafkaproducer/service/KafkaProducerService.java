package com.practice.kafkaproducer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerService {

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @Value("${practice.kafka.topic.name:default}")
    private String topic;

    public void sendMessage(String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        future.whenComplete((result, exception) -> {
            int partition = result.getRecordMetadata().partition();
            if(exception != null) {
                System.out.println("Exception in kafka send message " + exception.getMessage());
            }else{
                System.out.println("Sent Kafka Message : " + message + " with Offset "
                + result.getRecordMetadata().offset() + " and partition " + partition);
            }

        });
    }
}
