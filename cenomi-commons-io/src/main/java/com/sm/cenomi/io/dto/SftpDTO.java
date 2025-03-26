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

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SftpDTO {

  @NotEmpty(message = "userName should not be empty")
  @NotBlank(message = "userName should not be blank")
  @NotNull(message = "userName Should not be null")
  private String username;

  @NotEmpty(message = "host should not be empty")
  @NotBlank(message = "host should not be blank")
  @NotNull(message = "host Should not be null")
  private String host;

  @NotEmpty(message = "port should not be empty")
  @NotBlank(message = "port should not be blank")
  @NotNull(message = "port Should not be null")
  private Integer port;

  @NotEmpty(message = "password should not be empty")
  @NotBlank(message = "password should not be blank")
  @NotNull(message = "password Should not be null")
  private String password;

  @NotEmpty(message = "directory should not be empty")
  @NotBlank(message = "directory should not be blank")
  @NotNull(message = "directory Should not be null")
  private String directory;
}
