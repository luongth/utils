/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tal.core.utils;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Thuan Luong
 */
public interface ReverseLookupEnumSupport<T, E extends Enum<E> & HasValueEnum<T>> extends HasValueEnum<T> {

    Class<E> getDeclaringClass();

    default Optional<E> lookup(T value) {
        return Stream.of(getDeclaringClass().getEnumConstants())
                .filter(e->e.value().equals(value))
                .findFirst();
    }

}
