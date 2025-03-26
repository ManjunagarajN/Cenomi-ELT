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

import com.sm.cenomi.commons.msg.exception.DuplicateMessageException;
import com.sm.cenomi.commons.msg.exception.MessageValidationFailedException;
import com.sm.cenomi.commons.msg.service.DedupeCheckService;
import com.sm.cenomi.commons.msg.util.GenericTaskResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GenericEventHandler {

  public static final Character JSON_MESSAGE_IDENTIFIER = '{';

  private final DedupeCheckService dedupeCheckService;

  public GenericTaskResult handleMessage(
      final String message,
      final boolean isDupCheck,
      final String result,
      final String dedupeKey,
      EventHandler eventHandler) {
    GenericTaskResult taskResult;
//Step1
    checkDuplicate(dedupeKey, isDupCheck);
//Step2
    validate(message, result);
//Step3
    taskResult = handleMessage(message, eventHandler);

    return taskResult;
  }

  private void checkDuplicate(final String dedupeKey, final boolean isDupcheck) {// TODO: handle DLQ for if Dedupe fails
    if (isDupcheck && dedupeCheckService.isDuplicate(dedupeKey)) {
      throw new DuplicateMessageException(dedupeKey);
    }
  }

  private void validate(final String message, final String result) {
    if (!JSON_MESSAGE_IDENTIFIER.equals(message.charAt(0))) {
      throw new MessageValidationFailedException("message validation Failed");
    }
  }

  private GenericTaskResult handleMessage(final String message, EventHandler eventHandler) {
    return eventHandler.processMessage(message);
  }
}
