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

import java.util.function.Supplier;

public class LazyStringPlaceholder implements LazyPlaceholder<String>, StringPlaceholder<Supplier<String>> {

    private final String identifier;
    private final Supplier<String> value;

    public LazyStringPlaceholder(String identifier, Supplier<String> value) {
        this.identifier = identifier;
        this.value = value;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public Supplier<String> getValue() {
        return value;
    }

    @Override
    public String evaluate() {
        return value.get();
    }

}
