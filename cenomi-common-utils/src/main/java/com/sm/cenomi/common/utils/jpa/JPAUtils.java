package com.sm.cenomi.common.utils.jpa;

/*-
 * *****
 * cenomi-common-utils
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

import com.sm.cenomi.exception.handler.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;


public final class JPAUtils {
    public static <T, ID> T updateEntity(ID id, Map<String, Object> updates, JpaRepository<T, ID> repository) {
        return repository.findById(id).map((entity) -> {
            updates.forEach((fieldName, value) -> {
                Field field = ReflectionUtils.findField(entity.getClass(), fieldName);
                if (field != null) {
                    if(field.getType().isEnum())
                    {
                        Enum<?> enumValue = Enum.valueOf((Class<Enum>) field.getType(), String.valueOf(value));
                        ReflectionUtils.makeAccessible(field);
                        ReflectionUtils.setField(field, entity, enumValue);
                    }
                    else {
                        ReflectionUtils.makeAccessible(field);
                        ReflectionUtils.setField(field, entity, value);
                    }
                }

            });
            return repository.save(entity);
        }).orElseThrow(() -> {
            return new NotFoundException(String.format("%s not found", id));
        });
    }

    private JPAUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
