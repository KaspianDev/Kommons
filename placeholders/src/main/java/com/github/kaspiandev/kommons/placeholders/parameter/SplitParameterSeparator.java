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

import com.github.kaspiandev.kommons.placeholders.ParameterizedPlaceholder;

import java.util.Arrays;
import java.util.List;

public class SplitParameterSeparator implements ParameterSeparator {

    private final String separator;

    public SplitParameterSeparator(String separator) {
        this.separator = separator;
    }

    @Override
    public List<String> separate(ParameterizedPlaceholder<?, ?> placeholder) {
        return Arrays.stream(placeholder.getIdentifier().split(separator))
                     .toList();
    }

}
