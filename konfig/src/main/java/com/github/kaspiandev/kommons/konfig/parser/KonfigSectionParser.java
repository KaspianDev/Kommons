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

package com.github.kaspiandev.kommons.konfig.parser;

import com.github.kaspiandev.kommons.konfig.KonfigSection;
import com.github.kaspiandev.kommons.konfig.adapter.KonfigAdapter;
import com.github.kaspiandev.kommons.konfig.adapter.SerializationData;

public class KonfigSectionParser implements KonfigParser<KonfigSection> {

    @Override
    public <S> void encode(KonfigSection holder, KonfigAdapter<S> adapter, S subject) {
        SerializationData serializationData = new SerializationData(holder.properties());
        adapter.serialize(serializationData, subject);
    }

    @Override
    public <S> S decode(KonfigSection holder, KonfigAdapter<S> adapter) {
        SerializationData serializationData = new SerializationData(holder.properties());
        return adapter.deserialize(serializationData);
    }

}
