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

package com.github.kaspiandev.kommons.kolors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KolorTest {

    @Test
    void testWhite() {
        Kolor red = new Kolor(255, 255, 255);
        Assertions.assertEquals(red.getRed(), 255);
        Assertions.assertEquals(red.getGreen(), 255);
        Assertions.assertEquals(red.getBlue(), 255);
    }

    @Test
    void clampLow() {
        Kolor red = new Kolor(-1, -1, -1);
        Assertions.assertEquals(new Kolor(0, 0, 0), red);
    }

    @Test
    void clampHigh() {
        Kolor red = new Kolor(256, 256, 256);
        Assertions.assertEquals(new Kolor(255, 255, 255), red);
    }

}
