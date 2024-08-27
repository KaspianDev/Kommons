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

import java.util.regex.Pattern;

public class SimplePlaceholderFormatter implements PlaceholderFormatter {

    private final String prefix;
    private final String suffix;
    private final Pattern pattern;

    public SimplePlaceholderFormatter(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.pattern = compile();
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    private Pattern compile() {
        return Pattern.compile(Pattern.quote(getPrefix()) + "([^{}]+)" + Pattern.quote(getSuffix()));
    }

    @Override
    public Pattern getCompiledPattern() {
        return pattern;
    }

    @Override
    public int getGroup() {
        return 1;
    }

}
