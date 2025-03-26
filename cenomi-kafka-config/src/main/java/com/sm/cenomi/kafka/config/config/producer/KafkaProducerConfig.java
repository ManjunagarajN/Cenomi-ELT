package com.sm.cenomi.kafka.config.config.producer;

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
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.producer.ProducerConfig.ACKS_CONFIG;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableKafka
public class KafkaProducerConfig {

     private final ProducerProperties producerProperties;


     @Bean
     public ProducerFactory<String, String> producerFactory() {
          Map<String, Object> producer = new HashMap<>();
//        producer.put(LINGER_MS_CONFIG, customProducerProperties.getLingerMs());
//        producer.put(RETRIES_CONFIG, customProducerProperties.getRetries());
//        producer.put(COMPRESSION_TYPE_CONFIG, customProducerProperties.getCompressionType());
          producer.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, producerProperties.getBootstrapServers());
          producer.put(ProducerConfig.CLIENT_ID_CONFIG, "RI-SIREN");
          producer.put(ACKS_CONFIG, producerProperties.getAcks());

          producer.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
          producer.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

          producer.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
          producer.put(ProducerConfig.BATCH_SIZE_CONFIG, 0);

          JmsUtils.configureSSLProperties(producer, producerProperties);

          producer.forEach((k, v) -> {
               log.info("key :{} , value :{} ", k, v);
          });
          return new DefaultKafkaProducerFactory<>(producer);
     }


     @Bean
     public KafkaTemplate<String, String> createKafkaTemplate() {
          return new KafkaTemplate<>(producerFactory());
     }
}
