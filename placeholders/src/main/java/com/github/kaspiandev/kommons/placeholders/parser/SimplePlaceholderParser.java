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

import com.github.kaspiandev.kommons.placeholders.Placeholder;
import com.github.kaspiandev.kommons.placeholders.formatter.PlaceholderFormatter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

public class SimplePlaceholderParser implements PlaceholderParser<String> {

    private final Map<String, Placeholder<?, String>> placeholders;
    private final PlaceholderFormatter formatter;

    public SimplePlaceholderParser(PlaceholderFormatter formatter) {
        this.placeholders = new HashMap<>();
        this.formatter = formatter;
    }

    public void registerPlaceholder(Placeholder<?, String> placeholder) {
        placeholders.put(placeholder.getIdentifier(), placeholder);
    }

    @Override
    public Collection<Placeholder<?, String>> getPlaceholders() {
        return placeholders.values();
    }

    @Override
    public PlaceholderFormatter getFormatter() {
        return formatter;
    }

    @Override
    public String parse(String input) {
        StringBuilder result = new StringBuilder();
        int lastIndex = 0;

        Matcher matcher = formatter.compile().matcher(input);
        while (matcher.find()) {
            Placeholder<?, ?> placeholder = placeholders.get(matcher.group(1));
            if (placeholder == null) continue;

            result.append(input, lastIndex, matcher.start());
            result.append(placeholder.evaluate());

            lastIndex = matcher.end();
        }

        result.append(input.substring(lastIndex));

        return result.toString();
    }
}
