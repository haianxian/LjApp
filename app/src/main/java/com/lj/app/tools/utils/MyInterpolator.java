package com.lj.app.tools.utils;

import android.view.animation.Interpolator;

public class MyInterpolator implements Interpolator {
    @Override
    public float getInterpolation(float i) {
        return 2*i*i-2*i+1;
    }
}
