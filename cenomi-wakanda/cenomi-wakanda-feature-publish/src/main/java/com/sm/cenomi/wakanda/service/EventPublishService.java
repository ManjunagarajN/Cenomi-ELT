package com.sm.cenomi.wakanda.service;

import com.sm.cenomi.io.wakand.PublisherRequest;
import reactor.core.publisher.Mono;

public interface EventPublishService {
    Mono<String> publish(PublisherRequest publisherRequest);
}
