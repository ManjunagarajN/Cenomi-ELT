package com.sm.cenomi.commons.msg.handler;

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

import com.sm.cenomi.commons.msg.service.HashService;
import com.sm.cenomi.commons.msg.util.GenericTaskResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import static com.sm.cenomi.commons.msg.util.GenericTaskResult.SUCCESS;

@Slf4j
public abstract class EventHandler {

  @Autowired
  private HashService hashService;

  @Autowired
  private GenericEventHandler genericEventHandler;

  public abstract GenericTaskResult processMessage(String message);

  public GenericTaskResult handleMessage(final String message, final boolean isDupCheck) {
    GenericTaskResult result = SUCCESS;
    String dedupeKey = hashService.generateMd5HashKey(message);
    try {
      result = genericEventHandler.handleMessage(message, isDupCheck, null, dedupeKey, this);
    } catch (Exception e) {

      // TODO: handle the retries for messages
      log.error("Unable to handle the message :: {}", message);
    }
    return result;
  }
}
