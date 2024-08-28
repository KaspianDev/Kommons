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

package com.github.kaspiandev.kommons.placeholders.parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParameterParser {

    private final List<Parameter<?>> parameters;

    public ParameterParser() {
        this.parameters = new ArrayList<>();
    }

    public void registerParameter(Parameter<?> parameter) {
        parameters.add(parameter);
    }

    public <T> Optional<T> parseAs(ParameterStringValue stringValue, Class<T> clazz) {
        for (Parameter<?> parameter : parameters) {
            Optional<?> value = parameter.parseValue(stringValue);
            if (value.isEmpty()) continue;
            if (!clazz.isInstance(value.get())) continue;

            return Optional.of(clazz.cast(value.get()));
        }
        return Optional.empty();
    }

}
