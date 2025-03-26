package com.sm.cenomi.wakanda.event.publisher;

import com.sm.cenomi.io.wakand.PublisherRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public Mono<String> sendEvent(PublisherRequest publisherRequest) {
        return Mono.fromRunnable(() -> kafkaTemplate.send(publisherRequest.topic(), publisherRequest.payload())
                        .whenComplete((res, ex) -> {
                            if (ex != null) {
                                log.info("Failed to send the message :: {} ", publisherRequest.payload());
                            } else {
                                log.info("Message Sent successfully :: {} ", publisherRequest.payload());
                            }
                        }))
                .thenReturn("Successfully message published with message :" + publisherRequest.payload());
    }

}
