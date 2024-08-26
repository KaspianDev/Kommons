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

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

@SuppressWarnings("unchecked")
public class Konfig {

    private final Pattern separatorPattern;
    private final Yaml yaml;
    private final File file;
    private final Map<String, Object> configProperties;

    public Konfig(File file) {
        this.file = file;
        DumperOptions defaultDumperOptions = new DumperOptions();
        defaultDumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        this.yaml = new Yaml(defaultDumperOptions);
        this.configProperties = loadFile();
        this.separatorPattern = Pattern.compile(Pattern.quote("."));
    }

    public void reload() {
        configProperties.putAll(loadFile());
    }

    private Map<String, Object> loadFile() {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            if (configProperties != null && !configProperties.isEmpty()) {
                configProperties.clear();
            }
            Map<String, Object> loadedData = yaml.load(inputStream);
            return (loadedData == null)
                    ? new LinkedHashMap<>()
                    : loadedData;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void save() {
        save(file);
    }

    public String dumpRaw() {
        return yaml.dump(configProperties);
    }

    public void save(File outputFile) {
        try {
            FileWriter writer = new FileWriter(outputFile);
            yaml.dump(configProperties, writer);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private int getMaxLevel(String section) {
        return separatorPattern.split(section).length;
    }

    public KonfigSection getSection(String section) {
        Object object = get(section);
        if (object == null) {
            return null;
        }
        return (object instanceof Map<?, ?>)
                ? new KonfigSection(this, (Map<String, Object>) get(section))
                : new KonfigSection(this, new LinkedHashMap<>());
    }

    public KonfigSection createSection(String section) {
        KonfigSection possibleSection = getSection(section);
        if (possibleSection == null) {
            set(section, new LinkedHashMap<>());
            return getSection(section);
        }
        return possibleSection;
    }

    private boolean isSimple(String section) {
        return !section.contains(".");
    }

    public Object get(String section) {
        return (isSimple(section))
                ? getSimple(section)
                : getComplex(section);
    }

    private Object getComplex(String section) {
        Sektor sector = getSector(section);
        return sector.parentProperties().get(sector.lastLevel());
    }

    private Sektor getSector(String section) {
        int maxLevel = getMaxLevel(section);
        String[] levels = separatorPattern.split(section);
        String lastIndex = levels[levels.length - 1];
        String newSection = section.substring(0, section.lastIndexOf('.'));
        Map<String, Object> parentSection = getNestedSection(newSection, configProperties, maxLevel);
        return new Sektor(parentSection, lastIndex);
    }

    private Map<String, Object> getNestedSection(String section, Map<String, Object> currentLevel, int remainingLevels) {
        if (remainingLevels == 2) {
            return (Map<String, Object>) currentLevel.get(section);
        }
        String[] levels = separatorPattern.split(section);
        Map<String, Object> nextLevel = (Map<String, Object>) currentLevel.get(levels[0]);
        int level = remainingLevels - 1;
        return getNestedSection(section.substring(section.indexOf('.') + 1), nextLevel, level);
    }

    private Object getSimple(String section) {
        return configProperties.get(section);
    }

    public void set(String section, Object value) {
        if (isSimple(section)) {
            setSimple(section, value);
        } else {
            setComplex(section, value);
        }
    }

    private void setComplex(String section, Object value) {
        Sektor sector = getSector(section);
        sector.parentProperties().put(sector.lastLevel(), value);
    }

    private void setSimple(String section, Object value) {
        configProperties.put(section, value);
    }

    public Map<String, Object> getConfigProperties() {
        return configProperties;
    }

}
