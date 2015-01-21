package com.ymr.mathanim;

import android.graphics.PointF;
import android.util.Log;

/**
 * y = a(x-h) + k
 * Created by ymr on 15-1-17.
 */
public class ParabolicFunction extends MathFunction {

    private static final String TAG = "ParabolicFunction";
    private PointF vertice;
    private int verticeDirection = VERTICE_DIRECTION_UP;
    private float verticeY;
    private float a;

    private boolean hasSetP;
    public static int VERTICE_DIRECTION_UP = 0;
    public static int VERTICE_DIRECTION_DOWN = 1;

    @Override
    protected float getY(float x) {
        return (float) (a*Math.pow(x- vertice.x,2) + vertice.y);
    }

    public void setVerticeY(float verticeY) {
        hasSetP = true;
        this.verticeY = verticeY;
    }

    /**
     * @see #VERTICE_DIRECTION_DOWN
     * @see #VERTICE_DIRECTION_UP
     * @param verticeDirection
     */
    public void setVerticeDirection(int verticeDirection) {
        this.verticeDirection = verticeDirection;
    }

    @Override
    public void create(PointF start, PointF end) {
        super.create(start,end);
        float x = targetDis/2;
        float y = targetDis;
        if (hasSetP) {
            y = verticeY;
        }
        if (verticeDirection == VERTICE_DIRECTION_DOWN) {
            y = -y;
        }
        vertice = new PointF(x,y);
        a = (float) ((-vertice.y)/Math.pow(-vertice.x,2));
        Log.v(TAG,"vertice = " + vertice + " a = " + a);
    }
}
