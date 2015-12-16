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

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * @author Thuan Luong
 */
public class ReverseLookupEnumSupportTests {

    @Test
    public void integer_value_enum() {
        IntegerBasedEnum value = IntegerBasedEnum.fromValue(1);
        assertThat(value, is(notNullValue()));
        assertThat(value, is(IntegerBasedEnum.ONE));

        IntegerBasedEnum two = IntegerBasedEnum.fromValue(2);
        assertThat(value, is(not(equalTo(two))));

        try {
            IntegerBasedEnum.fromValue(3);
            fail("Should have thrown an IllegalArgumentException");
        } catch (Exception e) {
            assertThat(e, is(instanceOf(IllegalArgumentException.class)));
        }
    }

    @Test
    public void character_value_enum() {
        Optional<CharacterBasedEnum> value1 = CharacterBasedEnum.fromValue('C');
        assertThat(value1.isPresent(), is(true));
        assertThat(value1.get(), is(CharacterBasedEnum.C));
    }

    private static enum IntegerBasedEnum implements ReverseLookupEnumSupport<Integer, IntegerBasedEnum> {

        ONE (1), TWO (2);

        private final int value;

        IntegerBasedEnum(int value) {
            this.value = value;
        }

        public static IntegerBasedEnum fromValue(int value) {
            return ONE.lookup(value).orElseThrow(() -> new IllegalArgumentException());
        }

        @Override
        public Integer value() {
            return value;
        }
    }

    private static enum CharacterBasedEnum implements ReverseLookupEnumSupport<Character, CharacterBasedEnum> {

        C ('C'), D ('D');

        private final char value;

        CharacterBasedEnum(char value) {
            this.value = value;
        }

        public static Optional<CharacterBasedEnum> fromValue(char value) {
            return C.lookup(value);
        }

        @Override
        public Character value() {
            return value;
        }
    }
}
