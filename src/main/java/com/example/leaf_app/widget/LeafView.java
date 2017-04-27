package com.example.leaf_app.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.example.leaf_app.R;

/**
 * author : daiwenbo
 * e-mail : daiwwenb@163.com
 * date   : 2017/4/26
 * description   : xxxx描述
 */

public class LeafView extends AbLeafView {
    private Paint paint02;
    private PointF[] points;
    private PointF[] controls;
    private float mPercentX, mPercentY;//比例控制

    public LeafView(Context context) {
        super(context);
    }

    public LeafView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LeafView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LeafView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void createInit() {
        points=new PointF[5];
        controls=new PointF[3];
        for(int i=0;i<points.length;i++){
            points[i]=new PointF();
        }
        for(int i=0;i<controls.length;i++){
            controls[i]=new PointF();
        }
        paint02=new Paint();
        paint02.setStrokeWidth(1);
        paint02.setColor(default_color);
        paint02.setStyle(Paint.Style.STROKE);
        paint02.setPathEffect(new CornerPathEffect(4));//曲线圆角度
    }

    @Override
    protected void setData(int w, int h) {
        mPercentX=w/50;
        mPercentY=w/50;
        points[0].set(w / 2 - 2, 45);
        points[1].set(w / 2 - 2, 35);
        points[2].set(w / 2, 8);
        points[3].set(w / 2 + 2, 35);
        points[4].set(w / 2 + 2, 45);

        controls[0].set(9, 27);
        controls[1].set(41, 27);
        controls[2].set(30, 18);
    }

    @Override
    protected void leafDraw(Canvas canvas) {
        Path path=new Path();
        path.moveTo(points[0].x,points[0].y*mPercentY);
        path.lineTo(points[1].x,points[1].y*mPercentY);

        path.quadTo(controls[0].x*mPercentX,controls[0].y*mPercentY,points[2].x,points[2].y*mPercentY);
        path.quadTo(controls[1].x*mPercentX,controls[1].y*mPercentY,points[3].x,points[3].y*mPercentY);

        path.lineTo(points[4].x,points[4].y*mPercentY);
        path.lineTo(points[0].x,points[0].y*mPercentY);
        canvas.drawPath(path,paint);


        path.moveTo(points[1].x,points[1].y*mPercentY);
        path.quadTo(controls[2].x*mPercentX,controls[2].y*mPercentY,points[2].x,points[2].y*mPercentY);
        canvas.drawPath(path,paint02);
    }
}
