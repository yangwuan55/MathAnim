package com.ymr.mathanim;

import android.animation.Animator;

/**
 * y = ax^2 + bx + c
 * Created by ymr on 15-1-17.
 */
public class ParabolicFunction extends MathFunction {

    private float axisX;
    private float axisY;
    private float a,b,c;
    private boolean hasSetP;

    public void setABC(float a,float b,float c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    protected float getY(float x) {
        return (float) (a*Math.pow(x,2)+b*x+c);
    }

    @Override
    void setParameters(float... value) {
        hasSetP = true;
        a = value[0];
        b = value[1];
        c = value[2];
    }

    public void setAxisY(float axisY) {
        this.axisY = axisY;
    }

    @Override
    public Animator create(float x1, float x2) {
        if (!hasSetP) {
            axisX = (x1 > x2 ? x1 - x2:x2 - x1) / 2;

        }
        return super.create(x1, x2);
    }
}
