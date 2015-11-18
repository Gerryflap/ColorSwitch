package com.example.gerben.colorswitch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by gerben on 18-11-15.
 */
public class ColorInputView extends View {
    SweepGradient shader;
    Paint paint;
    Paint shadowInnerPaint;
    Paint shadowOuterPaint;
    double startA;
    double rotation;
    SweepGradient shadowShaderInner;
    SweepGradient shadowShaderOuter;


    public ColorInputView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setWillNotDraw(false);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);    // ImageView
        Logger.getLogger("ColorInputView").log(Level.INFO, "Redraw");
        canvas.rotate((float) -((rotation / (2.0 * Math.PI)) * 360.0f), getWidth() / 2, getHeight() / 2);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, Math.min(getHeight(), getWidth()) / 2 - 50, paint);
        canvas.rotate((float) ((rotation / (2.0 * Math.PI)) * 360.0f), getWidth() / 2, getHeight() / 2);

        canvas.rotate(90f, getWidth() / 2, getHeight() / 2);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, Math.min(getHeight(), getWidth()) / 2 - 75 - 2, shadowInnerPaint);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, Math.min(getHeight(), getWidth()) / 2 - 25 + 2, shadowOuterPaint);
        canvas.rotate(-90f, getWidth() / 2, getHeight() / 2);
        Logger.getLogger("ColorInputView").log(Level.INFO, String.valueOf(rotation));


    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        //Logger.getLogger("ColorInputView").log(Level.INFO, String.format("Height: %d, Width: %d, x: %f, y: %f, White: %x", getHeight(), getWidth(), getX(), getY(), Color.RED));
        shader = new SweepGradient(
                getWidth()/2,
                getHeight()/2,
                new int[] {
                        0xffff0000, // please input your color from resource for color-4
                        0xffffff00,
                        0xff00ff00,
                        0xff00ffff,
                        0xff0000ff,
                        0xffff00ff,
                        0xffff0000
                },
                null
        );
        shadowShaderInner = new SweepGradient(
                getWidth()/2,
                getHeight()/2,
                new int[] {
                        0x00444444,
                        0x00444444,
                        0xff444444,
                        0x00444444,
                        0x00444444,
                },
                null
        );
        shadowShaderOuter = new SweepGradient(
                getWidth()/2,
                getHeight()/2,
                new int[] {
                        0xff444444,
                        0x00444444,
                        0x00444444,
                        0x00444444,
                        0xff444444,
                },
                null
        );

        if (paint == null) {
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setDither(true);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStrokeWidth(50);
            shadowInnerPaint = new Paint();
            shadowOuterPaint = new Paint();
            shadowInnerPaint.setShader(shadowShaderInner);
            shadowOuterPaint.setShader(shadowShaderOuter);
            shadowInnerPaint.setStrokeWidth(4);
            shadowOuterPaint.setStrokeWidth(4);
            shadowOuterPaint.setStyle(Paint.Style.STROKE);
            shadowOuterPaint.setStrokeJoin(Paint.Join.ROUND);
            shadowOuterPaint.setStrokeCap(Paint.Cap.ROUND);
            shadowInnerPaint.setStyle(Paint.Style.STROKE);
            shadowInnerPaint.setStrokeJoin(Paint.Join.ROUND);
            shadowInnerPaint.setStrokeCap(Paint.Cap.ROUND);
            shadowInnerPaint.setAntiAlias(true);
            shadowInnerPaint.setDither(true);
            shadowOuterPaint.setAntiAlias(true);
            shadowOuterPaint.setDither(true);
        }
        paint.setShader(shader);
        super.onWindowFocusChanged(hasWindowFocus);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int action = event.getAction();
        if(action == MotionEvent.ACTION_DOWN) {
            startA = Math.atan2(getHeight()/2 - event.getY(), getWidth()/2 - event.getX());
            return true;
        } else if (action == MotionEvent.ACTION_MOVE) {
            this.callOnClick();
            double currentRotation = Math.atan2(getHeight()/2 - event.getY(), getWidth()/2 - event.getX());
            rotation += startA - currentRotation;
            rotation %= 2*Math.PI;
            startA = currentRotation;
            invalidate();
            return true;
        }
        return false;
    }

    public double getInputRotation() {
        return rotation;
    }

    public short[] getRGB() {
        short[] rgb = new short[3];
        for (int i = 0; i < 3; i++) {
            double value = colorFunction(rotation, -i/3.0f);
            Logger.getLogger("ColorInputView").log(Level.INFO, String.format("i: %d\t\t%d", i, (short) (value>=0?255*value:0)));
            rgb[i] = (short) (value>=0?255*value:0);
        }
        return rgb;
    }

    //offset is times tau (2*pi)
    private double colorFunction(double rotation, double offset) {
        rotation = (rotation/(2*Math.PI) + offset + 2 - 0.25)%(1);
        if (rotation >= 5f/6f || rotation <= 1f/6f) {
            System.out.println(rotation);
            return 1.0f;
        } else if(rotation >= 2f/6f && rotation <= 4f/6f) {
            return 0.0f;
        } else {
            if (rotation > 3f/6f) {
                return (rotation - 4/6.0) * 6;
            } else {
                return (1 - (rotation - 1/6.0) * 6);
            }
        }

    }
}
