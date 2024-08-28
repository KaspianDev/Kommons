/*
 * Copyright (C) 2024  Kaspian
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.kaspiandev.kommons.placeholders;

import com.github.kaspiandev.kommons.placeholders.parameter.IntegerParameter;
import com.github.kaspiandev.kommons.placeholders.parameter.ParameterParser;
import com.github.kaspiandev.kommons.placeholders.parameter.ParameterStringValue;
import com.github.kaspiandev.kommons.placeholders.parameter.StringParameter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PlaceholderTest {

    @Test
    void testFixedStringPlaceholder() {
        FixedStringPlaceholder placeholder = new FixedStringPlaceholder("test", "testValue");

        Assertions.assertEquals("testValue", placeholder.evaluate());
    }

    @Test
    void testLazyStringPlaceholder() {
        LazyStringPlaceholder placeholder = new LazyStringPlaceholder("test", () -> "testValue");

        Assertions.assertEquals("testValue", placeholder.evaluate());
    }

    @Test
    void testStringParameterizedPlaceholder() {
        StringParameterizedPlaceholder placeholder = new StringParameterizedPlaceholder() {

            @Override
            public String getIdentifier() {
                return "test:param1:param2";
            }

            @Override
            public String getValue() {
                ParameterParser parser = new ParameterParser();
                parser.registerParameter(new StringParameter());

                List<ParameterStringValue> parameters = getParameters();
                String param1 = parser.parseAs(parameters.get(0), String.class).orElseThrow();
                String param2 = parser.parseAs(parameters.get(1), String.class).orElseThrow();

                return param1 + " " + param2;
            }

            @Override
            public String evaluate() {
                return getValue();
            }

        };

        Assertions.assertEquals("param1 param2", placeholder.evaluate());
    }

    @Test
    void testIntParameterizedPlaceholder() {
        ParameterizedPlaceholder<Integer, Integer> placeholder = new ParameterizedPlaceholder<>() {

            @Override
            public String getIdentifier() {
                return "test:1:2";
            }

            @Override
            public Integer getValue() {
                ParameterParser parser = new ParameterParser();
                parser.registerParameter(new IntegerParameter());

                List<ParameterStringValue> parameters = getParameters();
                int param1 = parser.parseAs(parameters.get(0), Integer.class).orElseThrow();
                int param2 = parser.parseAs(parameters.get(1), Integer.class).orElseThrow();

                return param1 + param2;
            }

            @Override
            public Integer evaluate() {
                return getValue();
            }

        };

        Assertions.assertEquals(3, placeholder.evaluate());
    }

    @Test
    void testParameterizedPlaceholderIdentifier() {
        StringParameterizedPlaceholder placeholder = new StringParameterizedPlaceholder() {

            @Override
            public String getIdentifier() {
                return "test:param1:param2";
            }

            @Override
            public String getValue() {
                return "";
            }

            @Override
            public String evaluate() {
                return "";
            }

        };

        Assertions.assertEquals("test:param1:param2", placeholder.getIdentifier());
    }

}
