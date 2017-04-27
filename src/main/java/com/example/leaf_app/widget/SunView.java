package com.example.leaf_app.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * author : daiwenbo
 * e-mail : daiwwenb@163.com
 * date   : 2017/4/27
 * description   : xxxx描述
 */

public class SunView extends AbCloudView {
    private Paint paints;
    public SunView(Context context) {
        super(context);
    }

    public SunView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SunView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SunView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void createInit() {
        leaf_color= Color.parseColor("#F39455");
        paints=new Paint();
        paints.setAntiAlias(false);//抗锯齿
        paints.setDither(false);//柔和
        paints.setColor(leaf_color);
        paints.setStrokeWidth(8);
        paints.setStyle(Paint.Style.FILL);//充满
        points=new PointF[1];
        controls=new PointF[1];

    }

    @Override
    protected void setData(int w, int h) {
        mPercentX=w/100;
        mPercentY=h/100;
        points[0].set(42.12f,43.84f);
    }

    @Override
    protected void leafDraw(Canvas canvas) {
        Path path=new Path();
        path.addCircle(points[0].x*mPercentX,points[0].x*mPercentY,50, Path.Direction.CW);
        canvas.drawPath(path,paints);
    }
}
