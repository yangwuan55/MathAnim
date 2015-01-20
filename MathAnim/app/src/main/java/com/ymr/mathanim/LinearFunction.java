package com.ymr.mathanim;

import android.graphics.PointF;

/**
 * y = ax + b
 *
 * Created by ymr on 15-1-17.
 */
public class LinearFunction extends MathFunction {

    private float a,b;

    public void setAandB(float a, float b) {
        this.a = a;
        this.b = b;
    }

    @Override
    protected float getY(float x) {
        return a*x+b;
    }

    @Override
    void setParameters(float... value) {
        a = value[0];
        b = value[1];
    }

    @Override
    public void create(PointF start, PointF end) {

        super.create(start, end);
    }
}
