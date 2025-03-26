package com.sm.cenomi.commons.msg;

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

import static com.sm.cenomi.commons.msg.core.EventType.NO_OPS_Event;
import static com.sm.cenomi.commons.msg.util.GenericTaskResult.DISCARD;

import com.sm.cenomi.commons.msg.core.EventType;
import com.sm.cenomi.commons.msg.util.GenericTaskResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NoOpsEventListener implements AbstractEventService {
  @Override
  public GenericTaskResult processEvent(String message) {
    log.error("Invalid Event found, discarding the process :{}", message);
    return DISCARD;
  }

  @Override
  public EventType executeType() {
    return NO_OPS_Event;
  }
}
