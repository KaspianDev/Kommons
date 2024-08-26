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
import com.github.kaspiandev.kommons.kolors.interpolator.KolorInterpolator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GradientTransition implements BigTransition {

    private final KolorInterpolator interpolator;

    public GradientTransition(KolorInterpolator interpolator) {
        this.interpolator = interpolator;
    }

    @Override
    public List<Kolor> transition(Kolor fromKolor, Kolor toKolor, int size) {
        return transitionSection(fromKolor, toKolor, size);
    }

    @Override
    public List<Kolor> transition(List<Kolor> kolors, int size) {
        if (size == 1) return Collections.singletonList(kolors.get(0));

        int gradientSize = kolors.size();
        if (size == gradientSize) return kolors;
        if (gradientSize == 1) {
            return Collections.nCopies(size, kolors.get(0));
        } else if (gradientSize == 2) {
            return transition(kolors.get(0), kolors.get(1), size);
        }

        List<Kolor> interpolatedKolors = new ArrayList<>();
        int sections = gradientSize - 1;
        int sectionSize = size / sections;
        for (int i = 0; i < sections; i++) {
            Kolor currentKolor = kolors.get(i);
            Kolor nextKolor = kolors.get(i + 1);
            interpolatedKolors.addAll(transitionSection(currentKolor, nextKolor, sectionSize));
        }

        return interpolatedKolors;
    }

    private List<Kolor> transitionSection(Kolor startKolor, Kolor endKolor, int sectionSize) {
        List<Kolor> interpolatedKolors = new ArrayList<>();
        for (int i = 0; i < sectionSize; i++) {
            double ratio = (double) i / (sectionSize - 1);
            interpolatedKolors.add(interpolator.interpolate(startKolor, endKolor, ratio));
        }
        return interpolatedKolors;
    }

}
