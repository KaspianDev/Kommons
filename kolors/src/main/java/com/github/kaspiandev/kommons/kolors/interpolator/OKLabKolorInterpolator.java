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

public class OKLabKolorInterpolator implements KolorInterpolator {

    @Override
    public Kolor interpolate(Kolor sourceKolor, Kolor targetKolor) {
        if (sourceKolor.equals(targetKolor)) return sourceKolor;

        double[] sourceOKLab = rgbToOklab(sourceKolor);
        double[] targetOKLab = rgbToOklab(targetKolor);

        double l = interpolateTint(sourceOKLab[0], targetOKLab[0]);
        double a = interpolateTint(sourceOKLab[1], targetOKLab[1]);
        double b = interpolateTint(sourceOKLab[2], targetOKLab[2]);

        return oklabToRgb(l, a, b);
    }

    @Override
    public Kolor interpolate(Kolor sourceKolor, Kolor targetKolor, double ratio) {
        if (sourceKolor.equals(targetKolor)) return sourceKolor;

        double[] sourceOKLab = rgbToOklab(sourceKolor);
        double[] targetOKLab = rgbToOklab(targetKolor);

        double l = interpolateTint(sourceOKLab[0], targetOKLab[0], ratio);
        double a = interpolateTint(sourceOKLab[1], targetOKLab[1], ratio);
        double b = interpolateTint(sourceOKLab[2], targetOKLab[2], ratio);

        return oklabToRgb(l, a, b);
    }

    private double[] rgbToOklab(Kolor kolor) {
        double red = srgbToLinear(kolor.getRed() / 255.0);
        double green = srgbToLinear(kolor.getGreen() / 255.0);
        double blue = srgbToLinear(kolor.getBlue() / 255.0);

        double l = Math.cbrt(0.4121656120 * red + 0.5362752080 * green + 0.0514575653 * blue);
        double m = Math.cbrt(0.2118591070 * red + 0.6807189584 * green + 0.1074065790 * blue);
        double s = Math.cbrt(0.0883097947 * red + 0.2818474174 * green + 0.6302613616 * blue);

        double okl = 0.2104542553 * l + 0.7936177850 * m - 0.0040720468 * s;
        double okaa = 1.9779984951 * l - 2.4285922050 * m + 0.4505937099 * s;
        double okab = 0.0259040371 * l + 0.7827717662 * m - 0.8086757660 * s;

        return new double[]{okl, okaa, okab};
    }

    private Kolor oklabToRgb(double l, double a, double b) {
        double ll = Math.pow(l + 0.3963377774 * a + 0.2158037573 * b, 3);
        double mm = Math.pow(l - 0.1055613458 * a - 0.0638541728 * b, 3);
        double ss = Math.pow(l - 0.0894841775 * a - 1.2914855480 * b, 3);

        double r = 4.0767416621 * ll - 3.3077115913 * mm + 0.2309699292 * ss;
        double g = -1.2684380046 * ll + 2.6097574011 * mm - 0.3413193965 * ss;
        double blueChannel = -0.0041960863 * ll - 0.7034186147 * mm + 1.7076147010 * ss;

        int red = (int) (linearToSrgb(r) * 255.0);
        int green = (int) (linearToSrgb(g) * 255.0);
        int blue = (int) (linearToSrgb(blueChannel) * 255.0);

        return new Kolor(clamp(red), clamp(green), clamp(blue));
    }

    private double srgbToLinear(double srgb) {
        return (srgb <= 0.04045)
                ? srgb / 12.92
                : Math.pow((srgb + 0.055) / 1.055, 2.4);
    }

    private double linearToSrgb(double linear) {
        return (linear <= 0.0031308)
                ? 12.92 * linear
                : 1.055 * Math.pow(linear, 1.0 / 2.4) - 0.055;
    }

    private int clamp(int value) {
        return Math.max(0, Math.min(value, 255));
    }

    private double interpolateTint(double sourceTint, double targetTint) {
        return (sourceTint == targetTint)
                ? sourceTint
                : (sourceTint + targetTint) / 2;
    }

    private double interpolateTint(double sourceTint, double targetTint, double ratio) {
        return (sourceTint == targetTint)
                ? sourceTint
                : (int) (sourceTint + ratio * (targetTint - sourceTint));
    }

}
