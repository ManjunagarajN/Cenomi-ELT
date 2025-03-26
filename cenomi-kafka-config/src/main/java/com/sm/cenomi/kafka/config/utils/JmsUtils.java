package com.sm.cenomi.kafka.config.utils;

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

import com.sm.cenomi.kafka.config.config.SecurityProperties;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class JmsUtils {

     public static <T extends SecurityProperties> void configureSSLProperties(Map<String, Object> producer, T sp) {
          if (sp.getSslEnabled()) {
               producer.put("ssl.truststore.location", sp.getTsLocation());
               producer.put("ssl.truststore.password", sp.getTsPwd());
               producer.put("ssl.keystore.location", sp.getKsLocation());
               producer.put("ssl.keystore.password", sp.getKsPwd());
               producer.put("ssl.endpoint.identification.algorithm", sp.getEndPointAlgo());
               producer.put("security.protocol", sp.getSecurityProtocol());
          }
     }
}
