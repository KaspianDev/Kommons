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

}
