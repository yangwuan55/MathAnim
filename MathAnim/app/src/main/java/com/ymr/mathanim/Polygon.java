package com.ymr.mathanim;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.graphics.PointF;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ymr on 15-1-21.
 */
public class Polygon implements MathFunction.FunctionUpdateListener {

    private static final String TAG = "Polygon";
    protected List<MathFunction> edges = new ArrayList<MathFunction>();

    private View view;
    private int currAnimCount;

    public void create(PointF startPoint,MathFunction... functions) throws IllegalAccessException, InstantiationException {
        edges.clear();
        boolean isCrectStart = functions[0].getStartPoint().equals(startPoint);
        if (!isCrectStart) {
            MathFunction startFunction = functions[0].getClass().newInstance();
            startFunction.setDuration(1000);
            startFunction.create(startPoint, functions[0].getStartPoint());
            addFunction(startFunction);
        }

        for (MathFunction function : functions) {
            addFunction(function);
        }

        boolean isCrectEnd = functions[functions.length - 1].getEndPoint().equals(startPoint);
        if (!isCrectEnd) {
            MathFunction endFunction = functions[functions.length - 1].getClass().newInstance();
            endFunction.setDuration(1000);
            endFunction.create(functions[functions.length - 1].getEndPoint(),startPoint);
            addFunction(endFunction);
        }
    }

    private void addFunction(MathFunction function) {
        if (view != null) {
            function.setView(view);
        }
        edges.add(function);
    }

    public void start() {
        if (currAnimCount == edges.size()) {
            currAnimCount = 0;
            return;
        }
        MathFunction mathFunction = edges.get(currAnimCount);
        Log.v(TAG,"mathFunction = " + mathFunction);
        mathFunction.setView(view);
        mathFunction.addListener(this);
        mathFunction.start();
    }

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void onUpdate(float x, float y) {

    }

    @Override
    public void onEnd() {
        currAnimCount++;
        start();
    }
}
