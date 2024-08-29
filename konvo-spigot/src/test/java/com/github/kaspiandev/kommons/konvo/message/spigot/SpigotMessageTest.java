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

package com.github.kaspiandev.kommons.konvo.message.spigot;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpigotMessageTest {

    @Test
    void testComponentMessage() {
        BaseComponent[] component = TextComponent.fromLegacyText("Hello, World!");
        ComponentMessage message = new ComponentMessage(component);

        Assertions.assertEquals("§fHello, World!", BaseComponent.toLegacyText(message.getContents()));
    }

}
