package com.github.kaspiandev.kommons.kolors.util;

public class KolorUtil {

    private KolorUtil() {}

    public static int clampTint(int tint) {
        return Math.max(0, Math.min(tint, 255));
    }

}
