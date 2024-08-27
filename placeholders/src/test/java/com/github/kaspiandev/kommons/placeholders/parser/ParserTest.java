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

package com.github.kaspiandev.kommons.placeholders.parser;

import com.github.kaspiandev.kommons.placeholders.FixedStringPlaceholder;
import com.github.kaspiandev.kommons.placeholders.LazyStringPlaceholder;
import com.github.kaspiandev.kommons.placeholders.formatter.JSPlaceholderFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

public class ParserTest {

    @Test
    void testSimpleParserFixed() {
        JSPlaceholderFormatter formatter = new JSPlaceholderFormatter();
        SimplePlaceholderParser parser = new SimplePlaceholderParser(formatter);
        FixedStringPlaceholder placeholder = new FixedStringPlaceholder("hello", "Hello");
        parser.registerPlaceholder(placeholder);

        String input = "${hello}, World!";
        Assertions.assertEquals("Hello, World!", parser.parse(input));
    }

    @Test
    void testSimpleParserLazy() {
        JSPlaceholderFormatter formatter = new JSPlaceholderFormatter();
        SimplePlaceholderParser parser = new SimplePlaceholderParser(formatter);

        int random = ThreadLocalRandom.current().nextInt() * 100;
        LazyStringPlaceholder placeholder = new LazyStringPlaceholder("random", () -> {
            return String.valueOf(random);
        });
        parser.registerPlaceholder(placeholder);

        String input = "Random: ${random}";
        Assertions.assertEquals("Random: " + random, parser.parse(input));
    }

}
