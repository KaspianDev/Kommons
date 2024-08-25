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

public class BasicKolorInterpolator implements KolorInterpolator {

    @Override
    public Kolor interpolate(Kolor sourceKolor, Kolor targetKolor) {
        if (sourceKolor.equals(targetKolor)) return sourceKolor;

        int red = interpolateTint(sourceKolor.getRed(), targetKolor.getRed());
        int green = interpolateTint(sourceKolor.getGreen(), targetKolor.getGreen());
        int blue = interpolateTint(sourceKolor.getBlue(), targetKolor.getBlue());

        return new Kolor(red, green, blue);
    }

    @Override
    public Kolor interpolate(Kolor sourceKolor, Kolor targetKolor, double ratio) {
        if (ratio == 0.5) interpolate(sourceKolor, targetKolor);
        if (sourceKolor.equals(targetKolor)) return sourceKolor;

        int red = interpolateTint(sourceKolor.getRed(), targetKolor.getRed(), ratio);
        int green = interpolateTint(sourceKolor.getGreen(), targetKolor.getGreen(), ratio);
        int blue = interpolateTint(sourceKolor.getBlue(), targetKolor.getBlue(), ratio);

        return new Kolor(red, green, blue);
    }

    private int interpolateTint(int sourceTint, int targetTint) {
        return (sourceTint == targetTint)
                ? sourceTint
                : (sourceTint + targetTint) / 2;
    }

    private int interpolateTint(int sourceTint, int targetTint, double ratio) {
        return (sourceTint == targetTint)
                ? sourceTint
                : (int) (sourceTint + ratio * (targetTint - sourceTint));
    }

}
