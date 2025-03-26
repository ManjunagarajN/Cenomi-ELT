package com.sm.cenomi.wakanda.handler;

import com.sm.cenomi.io.wakand.PublisherRequest;
import com.sm.cenomi.wakanda.event.publisher.EventPublisher;
import com.sm.cenomi.wakanda.service.EventPublishService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EventPublishHandler implements EventPublishService {

    private final EventPublisher eventPublisher;

    @Override
    public Mono<String> publish(PublisherRequest publisherRequest) {
        if (publisherRequest.payload() == null || publisherRequest.payload().isEmpty()) {
            return Mono.error(new BadRequestException("Payload is null or empty"));
        }
        return eventPublisher.sendEvent(publisherRequest);
    }
}
