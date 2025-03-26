package com.sm.cenomi.io.onboarding.response;

/*-
 * *****
 * cenomi-commons-io
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

import com.sm.cenomi.enums.HttpMethods;
import lombok.Builder;

import java.util.Map;

@Builder(toBuilder = true)
public record APIResponse(
    Integer id,
    String name,
    String uri,
    String contextPath,
    String path,
    int sequence,
    int parentId,
    Boolean isLast,
    Boolean isPaginated,
    HttpMethods httpMethod,
    Map<String, String> headers,
    Map<Integer, Object> pathVariables,
    Map<String, String> requestParams,
    APIResponse parentApiResponse,
    Boolean isLogInAPI,
    String apiType,
    PaginatedResponse pageDetails,
    String identifier,
    String responseType
    ) {}
