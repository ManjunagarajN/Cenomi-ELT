package com.sm.cenomi.wakanda.event.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventListener {
    @KafkaListener(topics = {"test"}, groupId = "{test1}")
    public void readMessage(ConsumerRecord<String, String> data) {
        log.info("Consumed data : {}", data);
    }
}
