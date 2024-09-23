package com.practice.kafkaproducer.controller;


import com.practice.kafkaproducer.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(("/v1/kafka-producer"))
public class EventController {

    private final KafkaProducerService producer;

    @Autowired
    public EventController(KafkaProducerService producer) {
        this.producer = producer;
    }

    @GetMapping("/publish/{message}")
    public ResponseEntity<String> publishMessage(@PathVariable String message) {
        for(int i=0; i<1000; i++){
            producer.sendMessage(message);
        }
        return ResponseEntity.ok("Kafka Message published");
    }

}
