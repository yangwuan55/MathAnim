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
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Polygon polygon = new Polygon();
                polygon.setView(test);
                try {
                    polygon.create(new PointF(test.getX(), test.getY()), getMathFunction(new PointF(100, 200), new PointF(50, 60)),
                            getMathFunction(new PointF(50,60),new PointF(300,200)));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
                polygon.start();
                /*MathFunction mathFunction = new LinearFunction();
                mathFunction.setDuration(1000);
                mathFunction.moveTo(test,new PointF(test.getX()-150,test.getY()-300));*/
            }
        });
    }

    private MathFunction getMathFunction(PointF start,PointF end) {
        MathFunction mathFunction = new ParabolicFunction();
        mathFunction.create(start,end);
        mathFunction.setDuration(1000);
        return mathFunction;
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
