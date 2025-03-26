package com.sm.cenomi.wakanda.handler;

/*-
 * *****
 * cenomi-wakanda
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

import static com.sm.cenomi.wakanda.utils.CSVUtils.parseCsvData;
import static org.springframework.http.HttpStatus.CREATED;
import static reactor.core.publisher.Mono.error;
import static reactor.core.publisher.Mono.just;

import com.sm.cenomi.wakanda.entity.AppProperties;
import com.sm.cenomi.wakanda.repository.AppPropertiesRepository;
import com.sm.cenomi.wakanda.repository.AuditLogRepository;
import com.sm.cenomi.wakanda.service.WakandaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
@Slf4j
public class WakandaHandler implements WakandaService {

    private final AppPropertiesRepository appPropertiesRepository;

    private final AuditLogRepository auditLogRepository;

    @Override
    @Transactional
    public Flux<String> addProperties(Flux<FilePart> file) {

        return file.flatMap(
                filePart -> {
                    String fileName = filePart.filename();
                    return filePart
                            .content()
                            .map(dataBuffer -> parseCsvData(dataBuffer.asInputStream()))
                            .doOnError(error -> log.error("exception occurred while parsing the data"))
                            .map(
                                    s ->
                                            s.stream()
                                                    .map(
                                                            a ->
                                                                    AppProperties.builder()
                                                                            .application(a.getApplication())
                                                                            .label(a.getLabel())
                                                                            .profile(a.getProfile())
                                                                            .description(a.getDescription())
                                                                            .configKey(a.getConfigKey())
                                                                            .configValue(a.getConfigValue())
                                                                            .build())
                                                    .toList())
                            .flatMap(appPropertiesRepository::insert)
                            .log()
                            .then(just(CREATED.getReasonPhrase()));
                });
    }

    @Override
    public Flux<AppProperties> fetchProperties(String application) {
        return appPropertiesRepository
                .findByApplication(application)
                .switchIfEmpty(error(() -> new RuntimeException("application not found")));
    }
}
