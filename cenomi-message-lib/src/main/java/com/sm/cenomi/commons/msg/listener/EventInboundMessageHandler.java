package com.sm.cenomi.commons.msg.listener;

/*-
 * *****
 * cenomi-message-lib
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

import static com.sm.cenomi.commons.msg.util.GenericTaskResult.DISCARD;
import static com.sm.cenomi.commons.msg.util.GenericTaskResult.SUCCESS;

import com.sm.cenomi.commons.msg.EventLocator;
import com.sm.cenomi.commons.msg.handler.EventHandler;
import com.sm.cenomi.commons.msg.util.GenericTaskResult;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventInboundMessageHandler extends EventHandler {

  private final EventLocator eventLocator;

  @KafkaListener(
      topics = {"cenomi-event-inbound-channel"},
      groupId = "${cenomi-event-inbound-channel-group}")
  public void doTask(@Payload String payload, Acknowledgment ack) {
    GenericTaskResult result = super.handleMessage(payload, true);
    if (result.equals(SUCCESS) || result.equals(DISCARD)) {
      log.info("Message ia acknowledged with status :: {} ", result.name());
      ack.acknowledge();
    } else {
      ack.nack(0, Duration.ZERO);
      log.error("Failed to process message, will be retried again."); // TODO: DLQ setup
    }
  }

  @Override
  public GenericTaskResult processMessage(String message) {
    log.info("Started processing the event");
    return eventLocator
        .locate("abc") // Read Header and send label
        .processEvent(message);
  }
}
