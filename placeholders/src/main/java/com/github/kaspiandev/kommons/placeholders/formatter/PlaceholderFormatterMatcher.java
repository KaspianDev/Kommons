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

import java.util.regex.Matcher;

public class PlaceholderFormatterMatcher {

    private final PlaceholderFormatter formatter;
    private final Matcher matcher;

    public PlaceholderFormatterMatcher(PlaceholderFormatter formatter, String input) {
        this.formatter = formatter;
        this.matcher = formatter.getCompiledPattern().matcher(input);
    }

    public boolean find() {
        return matcher.find();
    }

    public String getCurrentGroup() {
        return matcher.group(formatter.getGroup());
    }

    public int start() {
        return matcher.start();
    }

    public int end() {
        return matcher.end();
    }

}
