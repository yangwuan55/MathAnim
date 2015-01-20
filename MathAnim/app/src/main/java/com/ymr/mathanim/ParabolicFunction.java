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

    public void setAxis(PointF axis) {
        hasSetP = true;
        this.axis = axis;
    }

    @Override
    public void create(PointF start, PointF end) {
        super.create(start,end);
        if (!hasSetP) {
            float x = targetDis/2;
            float y = targetDis;
            axis = new PointF(x,y);
            a = (float) ((-axis.y)/Math.pow(-axis.x,2));
            Log.v(TAG,"axis = " + axis + " a = " + a);
        }
    }
}
