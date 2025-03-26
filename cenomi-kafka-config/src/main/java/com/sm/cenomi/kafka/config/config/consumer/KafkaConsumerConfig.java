package com.sm.cenomi.kafka.config.config.consumer;

/*-
 * *****
 * cenomi-kafka-config
 * -------
 * Copyright (C) 2021 - 2024 RetailInsights
 * -------
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * ======
 */

import com.sm.cenomi.kafka.config.utils.JmsUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;
import static org.springframework.kafka.listener.ContainerProperties.AckMode.MANUAL_IMMEDIATE;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableKafka
public class KafkaConsumerConfig {

    private final ConsumerProperties consumerProperties;

    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        Map<String, Object> consumer = new HashMap<>();

        consumer.put(KEY_DESERIALIZER_CLASS_CONFIG, consumerProperties.getKeyDeserializer());
        consumer.put(VALUE_DESERIALIZER_CLASS_CONFIG, consumerProperties.getValueDeserializer());
        consumer.put(BOOTSTRAP_SERVERS_CONFIG, consumerProperties.getBootstrapServers());

        consumer.put(ENABLE_AUTO_COMMIT_CONFIG, false);
        consumer.put(MAX_POLL_INTERVAL_MS_CONFIG, 3000000);
        consumer.put(MAX_POLL_RECORDS_CONFIG, 1);
        consumer.put(REQUEST_TIMEOUT_MS_CONFIG, 60000);
        consumer.put(RETRY_BACKOFF_MS_CONFIG, 60000);
        consumer.put(ALLOW_AUTO_CREATE_TOPICS_CONFIG, true);

        consumer.put(AUTO_OFFSET_RESET_CONFIG, consumerProperties.getAutoOffsetReset());
        consumer.put(HEARTBEAT_INTERVAL_MS_CONFIG, 10000);
        consumer.put(SESSION_TIMEOUT_MS_CONFIG, 30000);

        consumer.put(CLIENT_ID_CONFIG, "RI-SIREN");

        JmsUtils.configureSSLProperties(consumer, consumerProperties);

        consumer.forEach((k, v) -> {
            log.info("key :{} , value :{} ", k, v);
        });

        return new DefaultKafkaConsumerFactory<>(consumer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.getContainerProperties().setAckMode(MANUAL_IMMEDIATE);
        factory.getContainerProperties().setSyncCommits(true);
        return factory;
    }
}
