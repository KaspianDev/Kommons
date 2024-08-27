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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class KonfigTest {

    private File file;

    @BeforeEach
    void prepareFile() throws IOException {
        this.file = new File("src/test/test.yml");
        Files.write(file.toPath(), List.of(
                "intValue: 1",
                "booleanValue: true",
                "doubleValue: 1.5",
                "stringValue: \"string\"",
                "testObject:",
                "  testString: \"test\"",
                "  testInt: 1",
                "  testObject:",
                "    testString: \"test2\"",
                "    testInt: 2",
                "testObject2:",
                "  testObject:",
                "    testString: \"test2\"",
                "    testInt: 2",
                "  testString: \"test3\"",
                "  testInt: 3"

        ));
        file.deleteOnExit();
    }

    @Test
    void testKonfigInit() {
        try {
            new Konfig(file);
        } catch (Exception ex) {
            Assertions.fail();
        }
    }

    @Test
    void testKonfigReadInteger() {
        Konfig konfig = new Konfig(file);
        Assertions.assertEquals(1, konfig.get("intValue"));
    }

    @Test
    void testKonfigReadBoolean() {
        Konfig konfig = new Konfig(file);
        Assertions.assertEquals(true, konfig.get("booleanValue"));
    }

    @Test
    void testKonfigReadDouble() {
        Konfig konfig = new Konfig(file);
        Assertions.assertEquals(1.5d, konfig.get("doubleValue"));
    }

    @Test
    void testKonfigReadString() {
        Konfig konfig = new Konfig(file);
        Assertions.assertEquals("string", konfig.get("stringValue"));
    }

    @Test
    void testKonfigReadObjectProperty() {
        Konfig konfig = new Konfig(file);
        konfig.registerAdapter(new TestObjectAdapter());
        Assertions.assertEquals("test", konfig.get("testObject", TestObject.class).testString());
    }

    @Test
    void testKonfigReadNestedObjectProperty() {
        Konfig konfig = new Konfig(file);
        konfig.registerAdapter(new TestObjectAdapter());
        Assertions.assertEquals("test2", konfig.get("testObject.testObject", TestObject.class).testString());
    }

    @Test
    void testKonfigReadNestedObject() {
        Konfig konfig = new Konfig(file);
        konfig.registerAdapter(new TestObjectAdapter());

        TestObject expected = new TestObject("test2", 2);
        Assertions.assertEquals(expected, konfig.get("testObject.testObject", TestObject.class));
    }

    @Test
    void testKonfigReadObjectInObject() {
        Konfig konfig = new Konfig(file);
        konfig.registerAdapter(new TestObject2Adapter());

        TestObject expected = new TestObject("test2", 2);
        Assertions.assertEquals(expected, konfig.get("testObject2", TestObject2.class).testObject());
    }

}
