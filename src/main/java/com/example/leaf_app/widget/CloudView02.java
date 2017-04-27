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

public class CloudView02 extends AbCloudView {
    public CloudView02(Context context) {
        super(context);
    }

    public CloudView02(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CloudView02(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CloudView02(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void createInit() {
        points=new PointF[4];
        controls=new PointF[6];
    }

    @Override
    protected void setData(int w, int h) {
        mPercentX=w/100;
        mPercentY=h/100;
        points[0].set(18.5f,52.5f);
        points[1].set(40.5f,48.5f);
        points[2].set(71.5f,47.5f);
        points[3].set(83.5f,53.5f);

        controls[0].set(26,41);
        controls[1].set(34,40);
        controls[2].set(45,27);
        controls[3].set(62,29);
        controls[4].set(75,39);
        controls[5].set(81,40);
    }

    @Override
    protected void leafDraw(Canvas canvas) {
        Path path=new Path();
        path.moveTo(points[0].x*mPercentX,points[0].y*mPercentY);
        path.cubicTo(controls[0].x*mPercentX,controls[0].y*mPercentY,controls[1].x*mPercentX,controls[1].y*mPercentY,points[1].x*mPercentX,points[1].y*mPercentY);
        path.cubicTo(controls[2].x*mPercentX,controls[2].y*mPercentY,controls[3].x*mPercentX,controls[3].y*mPercentY,points[2].x*mPercentX,points[2].y*mPercentY);
        path.cubicTo(controls[4].x*mPercentX,controls[4].y*mPercentY,controls[5].x*mPercentX,controls[5].y*mPercentY,points[3].x*mPercentX,points[3].y*mPercentY);
        path.lineTo(points[0].x*mPercentX,points[0].y*mPercentY);
        canvas.drawPath(path,paint);
    }
}
