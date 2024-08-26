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

package com.github.kaspiandev.kommons.konfig.adapter;

import com.github.kaspiandev.kommons.konfig.TestObject;
import com.github.kaspiandev.kommons.konfig.TestObjectAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class KonfigAdapterTest {

    private SerializationData testData;
    private TestObject testObject;

    @BeforeEach
    void prepareFile() {
        this.testData = new SerializationData(Map.of(
                "testString", "test",
                "testInt", 1
        ));
        this.testObject = new TestObject("test", 1);
    }

    @Test
    void testDeserialiation() {
        TestObjectAdapter adapter = new TestObjectAdapter();

        TestObject deserializedObject = adapter.deserialize(testData);
        Assertions.assertEquals(testObject, deserializedObject);
    }

    @Test
    void testSerialiation() {
        TestObjectAdapter adapter = new TestObjectAdapter();

        SerializationData expectedData = testData;
        SerializationData data = new SerializationData(new LinkedHashMap<>());

        adapter.serialize(data, testObject);
        Assertions.assertEquals(expectedData, data);
    }

}
