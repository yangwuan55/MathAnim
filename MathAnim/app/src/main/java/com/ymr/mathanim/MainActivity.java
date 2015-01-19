package com.ymr.mathanim;

import android.animation.Animator;
import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainActivity extends Activity implements MathFunction.FunctionUpdateListener {

    private static final String TAG = "MainActivity";
    private View test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test = findViewById(R.id.test);
        MathFunction mathFunction = new ParabolicFunction();
        mathFunction.addListener(this);
        Animator animator = mathFunction.create(new PointF(0,50), new PointF(200,50));
        animator.setDuration(3000);
        animator.start();
    }

    @Override
    public void onUpdate(float x, float y) {
        Log.v(TAG,"X = " + x + " y = " + y);
        test.setX(x);
        test.setY(y);
    }
}
