package com.sm.cenomi.io.dto;

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

import com.sm.cenomi.enums.ApiType;
import com.sm.cenomi.enums.HttpMethods;
import com.sm.cenomi.enums.ResponseType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiDTO {

  @NotEmpty(message = "Uri should not be empty")
  @NotBlank(message = "Uri should not be blank")
  @NotNull(message = "Uri Should not be null")
  private String uri;

  private String contextPath;

  @NotEmpty(message = "path should not be empty")
  @NotBlank(message = "path should not be blank")
  @NotNull(message = "path Should not be null")
  private String path;

  @NotEmpty(message = "name should not be empty")
  @NotBlank(message = "name should not be blank")
  @NotNull(message = "name Should not be null")
  private String name;

  private int sequence;

  @NotEmpty(message = "isPaginated should not be empty")
  @NotBlank(message = "isPaginated should not be blank")
  @NotNull(message = "isPaginated Should not be null")
  private Boolean isPaginated;

  private Boolean isLast;

  @NotEmpty(message = "httpMethod should not be empty")
  @NotBlank(message = "httpMethod should not be blank")
  @NotNull(message = "httpMethod Should not be null")
  private HttpMethods httpMethod;

  private Map<String, String> headers;

  private Map<String, Object> pathVariables;

  private Map<String, String> requestParams;

  private ApiType apiType;

  private PageDetailsDTO pageDetails;

  private String identifier;

  private ResponseType responseType;

  private AuthorizationDTO authorizationProperties;

  @NotEmpty(message = "isLogInAPI should not be empty")
  @NotBlank(message = "isLogInAPI should not be blank")
  @NotNull(message = "isLogInAPI Should not be null")
  private Boolean isLogInAPI;

  private String userName;

  private String password;

}
