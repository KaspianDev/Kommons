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

package com.github.kaspiandev.kommons.konfig;

import com.github.kaspiandev.kommons.konfig.adapter.KonfigAdapter;
import com.github.kaspiandev.kommons.konfig.adapter.SerializationData;

import java.util.Map;

public class TestObject2Adapter implements KonfigAdapter<TestObject2> {

    private final TestObjectAdapter adapter = new TestObjectAdapter();

    @Override
    @SuppressWarnings("unchecked")
    public TestObject2 deserialize(SerializationData data) {
        return new TestObject2(
                adapter.deserialize(new SerializationData((Map<String, Object>) data.data().get("testObject"))),
                (String) data.data().get("testString"),
                (Integer) data.data().get("testInt"));
    }

    @Override
    public void serialize(SerializationData data, TestObject2 subject) {
        adapter.serialize(data, subject.testObject());
        data.data().put("testString", subject.testString());
        data.data().put("testInt", subject.testInt());
    }

    @Override
    public Class<TestObject2> getSubjectClass() {
        return TestObject2.class;
    }

}
