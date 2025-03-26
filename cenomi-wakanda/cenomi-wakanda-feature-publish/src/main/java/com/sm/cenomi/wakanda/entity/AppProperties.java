package com.sm.cenomi.wakanda.entity;

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

import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.*;

@Table(value = "app_properties")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AppProperties {

  @Column(value = "application")
  @PrimaryKeyColumn(type = PARTITIONED)
  private String application;

  @Column(value = "profile")
  @PrimaryKeyColumn(type = PARTITIONED)
  private String profile;

  @Column(value = "label")
  @PrimaryKeyColumn(type = PARTITIONED)
  private String label;

  @PrimaryKeyColumn(name = "config_key")
  private String configKey;

  @PrimaryKeyColumn(name = "config_value")
  private String configValue;

  @Column(value = "description")
  @PrimaryKeyColumn
  private String description;
}
