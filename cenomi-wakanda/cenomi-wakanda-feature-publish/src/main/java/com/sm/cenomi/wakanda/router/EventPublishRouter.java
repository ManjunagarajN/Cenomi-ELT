package com.sm.cenomi.wakanda.router;

import com.sm.cenomi.io.wakand.PublisherRequest;
import com.sm.cenomi.wakanda.service.EventPublishService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class EventPublishRouter {

    private final EventPublishService eventPublishService;

    @PostMapping("/publish")
    public Mono<String> publish(@RequestBody PublisherRequest publisherRequest) {
        return eventPublishService.publish(publisherRequest);
    }
}

