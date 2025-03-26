package com.sm.cenomi.wakanda.utils;

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

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.sm.cenomi.wakanda.model.AppPropertiesData;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class CSVUtils {

  public static List<AppPropertiesData> parseCsvData(final byte[] file) {
    CsvMapper csvMapper = new CsvMapper();
    CsvSchema schema =
        csvMapper.schemaFor(AppPropertiesData.class).withHeader().withColumnReordering(true);
    try {
      return csvMapper
          .readerFor(AppPropertiesData.class)
          .with(schema)
          .<AppPropertiesData>readValues(file)
          .readAll();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static List<AppPropertiesData> parseCsvData(final InputStream stream) {
    CsvMapper csvMapper = new CsvMapper();
    CsvSchema schema =
            csvMapper.schemaFor(AppPropertiesData.class).withHeader().withColumnReordering(true);
    try {
      return csvMapper
                     .readerFor(AppPropertiesData.class)
                     .with(schema)
                     .<AppPropertiesData>readValues(stream)
                     .readAll();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
