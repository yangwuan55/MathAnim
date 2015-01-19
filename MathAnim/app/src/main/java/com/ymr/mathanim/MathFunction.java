package com.ymr.mathanim;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ymr on 15-1-17.
 */
public abstract class MathFunction {

    private float rotate;

    public interface FunctionUpdateListener{
        void onUpdate(float x,float y);
    }

    private List<FunctionUpdateListener> listeners = new ArrayList<FunctionUpdateListener>();

    public void addListener(FunctionUpdateListener listener) {
        listeners.add(listener);
    }

    public ValueAnimator.AnimatorUpdateListener animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            float currX = (Float) animation.getAnimatedValue();
            float currY = getY(currX);
            if (rotate != 0) {

            }
            for (FunctionUpdateListener listener : listeners) {
                listener.onUpdate(currX,currY);
            }
        }
    };

    /**
     * get y by x; y = f(x);
     * @param x
     * @return
     */
    protected abstract float getY(float x);

    /**
     * example: y = ax + b    , value is a and b.
     * @param value
     */
    abstract void setParameters(float... value);

    public void setRotate(float rotate) {
        this.rotate = rotate;
    }

    public Animator create(PointF start,PointF end) {
        float r = (float) Math.sqrt(Math.pow(Math.abs(start.x - end.x),2) + Math.pow(Math.abs(start.y - end.y),2));
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, r);
        valueAnimator.addUpdateListener(animatorUpdateListener);
        return valueAnimator;
    }

}
