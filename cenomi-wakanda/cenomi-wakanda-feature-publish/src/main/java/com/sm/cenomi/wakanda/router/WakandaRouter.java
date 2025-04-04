package com.sm.cenomi.wakanda.router;

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

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.*;
import com.sm.cenomi.wakanda.entity.AppProperties;
import com.sm.cenomi.wakanda.service.WakandaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class WakandaRouter {

  private final WakandaService wakandaService;

  @PostMapping(
      value = "/properties",
      consumes = MULTIPART_FORM_DATA_VALUE,
      produces = TEXT_EVENT_STREAM_VALUE)
  @ResponseStatus(value = CREATED)
  public Flux<String> addProperties(@RequestPart Flux<FilePart> file) {
    return wakandaService.addProperties(file);
  }

  @GetMapping(value = "/properties/{application}", produces = TEXT_EVENT_STREAM_VALUE)
  public Flux<AppProperties> fetchProperties(@PathVariable String application) {
    return wakandaService.fetchProperties(application);
  }
}
