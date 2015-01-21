package com.ymr.mathanim;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.graphics.PointF;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ymr on 15-1-17.
 */
public abstract class MathFunction implements Animator.AnimatorListener {

    protected float rotate;
    private ValueAnimator mAnimator;
    private long duration;
    private View view;
    private float originX;
    private float originY;
    protected float targetDis;
    private boolean canStart;

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        if (listeners.size() != 0) {
            for (FunctionUpdateListener listener : listeners) {
                listener.onEnd();
            }
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    public interface FunctionUpdateListener{
        void onUpdate(float x,float y);
        void onEnd();
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
                double radians = Math.toRadians(rotate);
                double tempX = currX*Math.cos(radians) - currY*Math.sin(radians);
                double tempY = currX*Math.sin(radians) + currY*Math.cos(radians);
                currX = (float) tempX;
                currY = (float) tempY;
            }
            if (listeners.size()!=0) {
                for (FunctionUpdateListener listener : listeners) {
                    listener.onUpdate(currX,currY);
                }
            }
            if (view != null) {
                view.setX((int)(originX + currX + 0.5f));
                view.setY((int)(originY + currY + 0.5f));
            }
        }
    };

    /**
     * get y by x; y = f(x);
     * @param x
     * @return
     */
    protected abstract float getY(float x);

    public void setRotate(float rotate) {
        this.rotate = rotate;
    }

    public void create(PointF start,PointF end) {
        if (start.equals(end)) {
            canStart = false;
        } else {
            canStart = true;
            float x = end.x - start.x;
            targetDis = (float) Math.sqrt(Math.pow(start.x - end.x,2) + Math.pow(start.y - end.y,2));
            if (end.x < start.x || end.y < start.y) {
                targetDis = -targetDis;
            }
            rotate = (float) Math.toDegrees(Math.acos(x/ targetDis));
            mAnimator = ValueAnimator.ofFloat(0, targetDis);
            mAnimator.addUpdateListener(animatorUpdateListener);
            mAnimator.addListener(this);
        }
    }

    public void start() {
        if (view != null) {
            originX = view.getX();
            originY = view.getY();
        }
        mAnimator.setDuration(duration);
        if (canStart) {
            mAnimator.start();
        }
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void moveTo(View view,PointF target) {
        this.view = view;
        create(new PointF(view.getX(),view.getY()),target);
        start();
    }

    public void moveTo(PointF target) {
        create(new PointF(view.getX(),view.getY()),target);
        start();
    }

    public ValueAnimator getAnimator() {
        return mAnimator;
    }
}
