package com.example.edwards_inc.gestureoverlay;

import android.content.Context;
import android.gesture.Gesture;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.RectShape;
import android.view.View;

/**
 * Created by Kobe on 9/13/2017.
 */

public class PlayingField extends View {
    private ShapeDrawable shape;
    private Path path;
    private int x;
    private int y;
    private int left;
    private int right;
    private int top;
    private int bottom;
    private boolean retVal;

    public PlayingField(Context context) {
        super(context);
        shape = null;
        path = null;
        x = 0;
        y = 0;
        retVal = false;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }
    
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(shape != null)
            shape.draw(canvas);
    }

    public boolean Parse(Gesture toParse, String Name) {
        x = (int) toParse.getBoundingBox().centerX();
        y = (int) toParse.getBoundingBox().centerY();
        int width = right - left;
        int height = bottom - top;
        path = toParse.toPath();
        switch(Name) {
            case "Square":
                shape = new ShapeDrawable(new RectShape());
                shape.setBounds(x-100, y-100, x+100, y+100);
                break;
            case "Circle":
                shape = new ShapeDrawable(new OvalShape());
                shape.setBounds(x-100, y-100, x+100, y+100);
                break;
            case "Line":
                shape = new ShapeDrawable(new PathShape(path, width, height));
                shape.setBounds(left, top, right, bottom);
                break;
            case "Triangle":
                shape = new ShapeDrawable(new PathShape(path, width, height));
                shape.setBounds(left, top, right, bottom);
                break;
            case "Z":
                shape = new ShapeDrawable(new PathShape(path, width, height));
                shape.setBounds(left, top, right, bottom);
                break;
            default:
                return false;
        }
        return true;
    }
}
