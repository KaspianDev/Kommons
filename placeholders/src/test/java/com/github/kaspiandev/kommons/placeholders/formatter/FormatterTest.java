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

package com.github.kaspiandev.kommons.placeholders.formatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;

public class FormatterTest {

    @Test
    void testFormatSimple() {
        SimplePlaceholderFormatter formatter = new SimplePlaceholderFormatter("{", "}");

        Matcher matcher = formatter.getCompiledPattern().matcher("{test}");
        matcher.find();
        String groupFound = matcher.group(formatter.getGroup());

        Assertions.assertEquals("test", groupFound);
    }

    @Test
    void testFormatMatcher() {
        SimplePlaceholderFormatter formatter = new SimplePlaceholderFormatter("{", "}");
        PlaceholderFormatterMatcher matcher = new PlaceholderFormatterMatcher(formatter, "{test}");

        matcher.find();
        String groupFound = matcher.getCurrentGroup();

        Assertions.assertEquals("test", groupFound);
    }

}
