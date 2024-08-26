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

package com.github.kaspiandev.kommons.kolors.interpolator;

import com.github.kaspiandev.kommons.kolors.Kolor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KolorInterpolatorTest {

    private Kolor black;
    private Kolor white;

    @BeforeEach
    void prepareTestColors() {
        this.black = new Kolor(0, 0, 0);
        this.white = new Kolor(255, 255, 255);
    }

    @Test
    void testBasic() {
        BasicKolorInterpolator interpolator = new BasicKolorInterpolator();

        Kolor gray = new Kolor(127, 127, 127);
        Kolor interpolatedKolor = interpolator.interpolate(black, white);
        Assertions.assertEquals(gray, interpolatedKolor);
    }

    @Test
    void testBasicRatio() {
        BasicKolorInterpolator interpolator = new BasicKolorInterpolator();

        Kolor darkGray = new Kolor(25, 25, 25);
        Kolor interpolatedKolor = interpolator.interpolate(black, white, 0.1);
        Assertions.assertEquals(darkGray, interpolatedKolor);
    }

    @Test
    void testOKLab() {
        OKLabKolorInterpolator interpolator = new OKLabKolorInterpolator();

        Kolor gray = new Kolor(99, 99, 99);
        Kolor interpolatedKolor = interpolator.interpolate(black, white);
        Assertions.assertEquals(gray, interpolatedKolor);
    }

    @Test
    void testOKLabRatio() {
        OKLabKolorInterpolator interpolator = new OKLabKolorInterpolator();

        Kolor darkGray = new Kolor(3, 3, 3);
        Kolor interpolatedKolor = interpolator.interpolate(black, white, 0.1);
        Assertions.assertEquals(darkGray, interpolatedKolor);
    }


}
