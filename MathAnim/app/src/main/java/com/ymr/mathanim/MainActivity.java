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
        final ParabolicFunction mathFunction = new ParabolicFunction();
        mathFunction.setDuration(1000);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathFunction.setVerticeY(-1000);
                mathFunction.moveTo(test,new PointF(600,400));
            }
        });
    }

    @Override
    public void onUpdate(float x, float y) {
        Log.v(TAG, "X = " + x + " y = " + y);
        test.setTranslationX(x);
        test.setTranslationY(y);
    }

    @Override
    public void onEnd() {

    }
}
