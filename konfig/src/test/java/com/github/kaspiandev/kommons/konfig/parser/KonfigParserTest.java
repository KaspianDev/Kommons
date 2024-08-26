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

import com.github.kaspiandev.kommons.konfig.Konfig;
import com.github.kaspiandev.kommons.konfig.KonfigSection;
import com.github.kaspiandev.kommons.konfig.TestObject;
import com.github.kaspiandev.kommons.konfig.TestObjectAdapter;
import com.github.kaspiandev.kommons.konfig.adapter.SerializationData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.List;

public class KonfigParserTest {

    private File file;
    private TestObject testObject;

    @BeforeEach
    void prepareFile() throws IOException {
        this.file = new File("src/test/test.yml");
        Files.write(file.toPath(), List.of(
                "testObject: ",
                "  testString: \"test\"",
                "  testInt: 1"
        ));
        this.testObject = new TestObject("test", 1);
        file.deleteOnExit();
    }

    @Test
    void testKonfigParserEncode() {
        Konfig konfig = new Konfig(file);
        TestObjectAdapter adapter = new TestObjectAdapter();
        KonfigSectionParser parser = new KonfigSectionParser();
        KonfigSection section = konfig.createSection("testObject2");

        TestObject testObject2 = new TestObject("test2", 2);
        parser.encode(section, adapter, testObject2);

        Assertions.assertEquals(testObject2, parser.decode(konfig.getSection("testObject2"), adapter));
    }

    @Test
    void testKonfigParserDecodeLoad() {
        Konfig konfig = new Konfig(file);
        TestObjectAdapter adapter = new TestObjectAdapter();
        KonfigSectionParser parser = new KonfigSectionParser();

        Assertions.assertEquals(testObject, parser.decode(konfig.getSection("testObject"), adapter));
    }

    @Test
    void testKonfigParserDecodeSave() {
        Konfig konfig = new Konfig(file);
        TestObjectAdapter adapter = new TestObjectAdapter();
        SerializationData data = new SerializationData(new LinkedHashMap<>());
        adapter.serialize(data, testObject);
        konfig.set("testObject", data.data());

        KonfigSectionParser parser = new KonfigSectionParser();
        Assertions.assertEquals(testObject, parser.decode(konfig.getSection("testObject"), adapter));
    }

}
