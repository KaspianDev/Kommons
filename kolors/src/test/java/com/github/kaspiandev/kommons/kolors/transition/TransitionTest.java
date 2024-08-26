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

package com.github.kaspiandev.kommons.kolors.transition;

import com.github.kaspiandev.kommons.kolors.Kolor;
import com.github.kaspiandev.kommons.kolors.interpolator.BasicKolorInterpolator;
import com.github.kaspiandev.kommons.kolors.interpolator.OKLabKolorInterpolator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TransitionTest {

    private Kolor black;
    private Kolor white;

    @BeforeEach
    void prepareTestColors() {
        this.black = new Kolor(0, 0, 0);
        this.white = new Kolor(255, 255, 255);
    }

    @Test
    void testBlackToWhiteGradient() {
        BasicKolorInterpolator interpolator = new BasicKolorInterpolator();
        GradientTransition transition = new GradientTransition(interpolator);

        List<Kolor> colors = transition.transition(List.of(black, white), 3);
        Assertions.assertEquals(new Kolor(127, 127, 127), colors.get(1));
    }

    @Test
    void testSimpleBlackToWhiteGradient() {
        BasicKolorInterpolator interpolator = new BasicKolorInterpolator();
        GradientTransition transition = new GradientTransition(interpolator);

        List<Kolor> colors = transition.transition(black, white, 3);
        Assertions.assertEquals(new Kolor(127, 127, 127), colors.get(1));
    }

    @Test
    void testBlackToWhiteLongGradient() {
        BasicKolorInterpolator interpolator = new BasicKolorInterpolator();
        GradientTransition transition = new GradientTransition(interpolator);

        List<Kolor> colors = transition.transition(List.of(black, white), 11);
        Assertions.assertEquals(new Kolor(127, 127, 127), colors.get(5));
    }

    @Test
    void testSimpleBlackToWhiteLongGradient() {
        BasicKolorInterpolator interpolator = new BasicKolorInterpolator();
        GradientTransition transition = new GradientTransition(interpolator);

        List<Kolor> colors = transition.transition(black, white, 11);
        Assertions.assertEquals(new Kolor(127, 127, 127), colors.get(5));
    }

    @Test
    void testBlackToWhiteOKLabGradient() {
        OKLabKolorInterpolator interpolator = new OKLabKolorInterpolator();
        GradientTransition transition = new GradientTransition(interpolator);

        List<Kolor> colors = transition.transition(List.of(black, white), 3);
        Assertions.assertEquals(new Kolor(99, 99, 99), colors.get(1));
    }

    @Test
    void testSimpleBlackToWhiteOKLabGradient() {
        OKLabKolorInterpolator interpolator = new OKLabKolorInterpolator();
        GradientTransition transition = new GradientTransition(interpolator);

        List<Kolor> colors = transition.transition(black, white, 3);
        Assertions.assertEquals(new Kolor(99, 99, 99), colors.get(1));
    }

    @Test
    void testBlackToWhiteLongOKLabGradient() {
        OKLabKolorInterpolator interpolator = new OKLabKolorInterpolator();
        GradientTransition transition = new GradientTransition(interpolator);

        List<Kolor> colors = transition.transition(List.of(black, white), 11);
        Assertions.assertEquals(new Kolor(99, 99, 99), colors.get(5));
    }

    @Test
    void testSimpleBlackToWhiteLongOKLabGradient() {
        OKLabKolorInterpolator interpolator = new OKLabKolorInterpolator();
        GradientTransition transition = new GradientTransition(interpolator);
        
        List<Kolor> colors = transition.transition(black, white, 11);
        Assertions.assertEquals(new Kolor(99, 99, 99), colors.get(5));
    }

}
