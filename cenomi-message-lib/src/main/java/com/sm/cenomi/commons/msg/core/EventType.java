package com.sm.cenomi.commons.msg.core;

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

import com.sm.cenomi.commons.msg.exception.InvalidEventTypeException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import lombok.Getter;

@Getter
public enum EventType {

  EVENT_CORE_SCHEDULER("EC_S"),

  SCHEDULER_REST_EXTRACTOR("S_RX"),

  REST_EXTRACTOR_CANONICAL("RX_C"),

  CANONICAL_EVENT_LOG("CP_EL"),

  NO_OPS_Event("No_Ops");

  private final String label;

  private static final Map<String, EventType> labelMap = new HashMap<>();

  static {
    for (EventType eventType : EventType.values()) {
      labelMap.put(eventType.label.toLowerCase(), eventType); // lowercase to protect bean
    }
  }

  EventType(String label) {
    this.label = label;
  }

  public static EventType eventByLabel(String label) {
    return Optional.ofNullable(
            labelMap.get(label.toLowerCase())) // checking with lowercase to protect bean to cast
        .orElseThrow(() -> new InvalidEventTypeException("Invalid event type label: " + label));
  }
}
