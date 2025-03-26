package com.sm.cenomi.kafka.config.config.producer;

import com.sm.cenomi.kafka.config.annotation.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class KafkaProducerAspect {
    private final KafkaTemplate<String,String> kafkaTemplate;

    @Pointcut("@annotation(kafkaProducer)")
    public void kafkaProducerPointcut(KafkaProducer kafkaProducer) {
    }

    @Around(value = "kafkaProducerPointcut(kafkaProducer)", argNames = "joinPoint,kafkaProducer")
    public Object handleKafkaProducer(ProceedingJoinPoint joinPoint, KafkaProducer kafkaProducer) throws Throwable {
        Object proceed = joinPoint.proceed();
//        String data = marshal(proceed);
        log.info("Sending message to Kafka. Topic: {}, Value: {}", kafkaProducer.topic(), proceed);
        kafkaTemplate.send(kafkaProducer.topic(), proceed.toString())
                .whenComplete(
                        (res, ex) -> {
                            if (ex != null) {
                                log.info("Failed to send the message :: {} ", proceed);
                            } else {
                                log.info("Message Sent successfully :: {} ", proceed);
                            }
                        });
        return proceed;
    }
}
