package com.ymr.mathanim;

import android.animation.Animator;
import android.graphics.PointF;
import android.util.Log;

/**
 * y = a(x-h) + k
 * Created by ymr on 15-1-17.
 */
public class ParabolicFunction extends MathFunction {

    private static final String TAG = "ParabolicFunction";
    private PointF axis;
    private float a;
    private boolean hasSetP;

    @Override
    protected float getY(float x) {
        return (float) (a*Math.pow(x-axis.x,2) + axis.y);
    }

    @Override
    void setParameters(float... value) {
        hasSetP = true;
        a = value[0];
        axis = new PointF(value[1],value[2]);
    }

    @Override
    public Animator create(PointF start, PointF end) {
        if (!hasSetP) {
            float x = (float) Math.sqrt(Math.pow(end.x - start.x, 2) + Math.pow(end.y - start.y, 2))/2;
            float y = 2*x;
            axis = new PointF(x,y);
            a = (float) ((end.y-start.y)/(Math.pow(start.x-axis.x,2)-Math.pow(end.x-axis.x,2)));
            Log.v(TAG,"axis = " + axis + " a = " + a);
        }

        return super.create(start, end);
    }
}
