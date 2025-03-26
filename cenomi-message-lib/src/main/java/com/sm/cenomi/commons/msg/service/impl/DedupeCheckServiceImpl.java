package com.sm.cenomi.commons.msg.service.impl;

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

import com.sm.cenomi.commons.dedupe.service.DedupeOutBoundPort;
import com.sm.cenomi.commons.msg.exception.CenomiConnectCoreException;
import com.sm.cenomi.commons.msg.service.DedupeCheckService;
import com.sm.cenomi.commons.msg.service.HashService;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DedupeCheckServiceImpl implements DedupeCheckService {

  private final HashService hashService;

  private final DedupeOutBoundPort dedupeOutBoundPort;

  @Override
  public boolean isDuplicate(String key) {
    return Optional.ofNullable(dedupeOutBoundPort)
        .map(port -> !port.isNotDuplicate(hashService.generateHash(key)))
        .orElseThrow(
            () -> {
              log.error(
                  "Dedupe Route configured incorrectly, try adding db type as one of the: "
                      + "Relational/Apache-Cassandra/Astra-Cassandra ");
              return new CenomiConnectCoreException(
                  "Dedupe Route configured incorrectly, try adding db type as one of the: Relational/Apache-Cassandra/Astra-Cassandra");
            });
  }
}
