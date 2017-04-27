package com.example.leaf_app.widget;

import android.content.Context;
import android.graphics.Canvas;
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

public class CloudView extends AbCloudView {
    public CloudView(Context context) {
        super(context);
    }

    public CloudView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CloudView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CloudView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void createInit() {
        points=new PointF[3];
        controls=new PointF[4];
        for(int i=0;i<points.length;i++){
            points[i]=new PointF();
        }
        for(int i=0;i<controls.length;i++){
            controls[i]=new PointF();
        }
    }

    @Override
    protected void setData(int w, int h) {
        mPercentX=w/100;
        mPercentY=h/100;
        points[0].set(18.5f,47.5f);
        points[1].set(39.5f,43.5f);
        points[2].set(67.5f,42.5f);

        controls[0].set(27,35);
        controls[1].set(34,34);
        controls[2].set(48,27);
        controls[3].set(59,27);
    }

    @Override
    protected void leafDraw(Canvas canvas) {
        Path path=new Path();
        path.moveTo(points[0].x*mPercentX,points[0].y*mPercentY);
        path.cubicTo(controls[0].x*mPercentX,controls[0].y*mPercentY,controls[1].x*mPercentX,controls[1].y*mPercentY,points[1].x*mPercentX,points[1].y*mPercentY);
        path.cubicTo(controls[2].x*mPercentX,controls[2].y*mPercentY,controls[3].x*mPercentX,controls[3].y*mPercentY,points[2].x*mPercentX,points[2].y*mPercentY);
        path.lineTo(points[0].x*mPercentX,points[0].y*mPercentY);
        canvas.drawPath(path,paint);
    }
}
