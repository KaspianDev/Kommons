package com.github.kaspiandev.kommons.kolors;

import java.util.Objects;

public class Kolor {

    private int red;
    private int green;
    private int blue;

    public Kolor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public Kolor() {
        this(0, 0, 0);
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Kolor kolor)) return false;
        return red == kolor.red && green == kolor.green && blue == kolor.blue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(red, green, blue);
    }

}
