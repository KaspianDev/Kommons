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
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@Fork(2)
public class TransitionBenchmark {

    private Kolor black;
    private Kolor white;

    @Param({"3", "5", "11", "51"})
    private int size;

    @Setup(Level.Trial)
    public void setUp() {
        this.black = new Kolor(0, 0, 0);
        this.white = new Kolor(255, 255, 255);
    }

    @Benchmark
    @Warmup(iterations = 5, time = 250, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 500, timeUnit = TimeUnit.MILLISECONDS)
    public void benchmarkBlackToWhiteGradient(Blackhole blackhole) {
        BasicKolorInterpolator interpolator = new BasicKolorInterpolator();
        GradientTransition transition = new GradientTransition(interpolator);

        blackhole.consume(transition.transition(black, white, size));
    }

    @Benchmark
    @Warmup(iterations = 5, time = 250, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 500, timeUnit = TimeUnit.MILLISECONDS)
    public void benchmarkBlackToWhiteOKLabGradient(Blackhole blackhole) {
        OKLabKolorInterpolator interpolator = new OKLabKolorInterpolator();
        GradientTransition transition = new GradientTransition(interpolator);

        blackhole.consume(transition.transition(black, white, size));
    }

}
