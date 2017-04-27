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

public class FengLeafView extends AbLeafView {
    private PointF[] points;
    private Paint paint01;
    private int default_color= Color.parseColor("#888888");
    public FengLeafView(Context context) {
        super(context);
    }

    public FengLeafView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FengLeafView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FengLeafView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void createInit() {
        paint01=new Paint();
        paint01.setAntiAlias(true);//抗锯齿
        paint01.setDither(true);//柔和
        paint01.setColor(default_color);
        paint01.setStrokeWidth(8);
        paint01.setStyle(Paint.Style.FILL);//充满
        paint01.setPathEffect(new CornerPathEffect(4));//曲线圆角度
        points= new PointF[23];
        for (int i = 0; i < points.length; i++) {
            points[i] = new PointF();
        }

    }

    @Override
    protected void setData(int w,int h) {
        mPercentX=w/60;
        mPercentY=h/60;
        points[0].set(49,6);
        points[1].set(53,14);
        points[2].set(58,13);
        points[3].set(56,24);
        points[4].set(62,19);
        points[5].set(63,23);
        points[6].set(69,22);
        points[7].set(67,28);
        points[8].set(71,30);
        points[9].set(59,40);
        points[10].set(60,43);

        points[11].set(49,41);
        points[12].set(37,43);
        points[13].set(38,39);
        points[14].set(28,30);
        points[15].set(30,29);
        points[16].set(29,21);
        points[17].set(36,23);
        points[18].set(36,19);
        points[19].set(43,24);
        points[20].set(41,12);
        points[21].set(45,14);
        points[22].set(49,53);
    }

    @Override
    protected void leafDraw(Canvas canvas) {
        Path path=new Path();
        path.moveTo(points[22].x*mPercentX,points[22].y*mPercentY);
        path.lineTo(points[11].x*mPercentX,points[11].y*mPercentY);

        path.moveTo(points[0].x*mPercentX,points[0].y*mPercentY);
        path.lineTo(points[1].x*mPercentX,points[1].y*mPercentY);
        path.lineTo(points[2].x*mPercentX,points[2].y*mPercentY);
        path.lineTo(points[3].x*mPercentX,points[3].y*mPercentY);
        path.lineTo(points[4].x*mPercentX,points[4].y*mPercentY);
        path.lineTo(points[5].x*mPercentX,points[5].y*mPercentY);
        path.lineTo(points[6].x*mPercentX,points[6].y*mPercentY);
        path.lineTo(points[7].x*mPercentX,points[7].y*mPercentY);
        path.lineTo(points[8].x*mPercentX,points[8].y*mPercentY);
        path.lineTo(points[9].x*mPercentX,points[9].y*mPercentY);
        path.lineTo(points[10].x*mPercentX,points[10].y*mPercentY);
        path.lineTo(points[11].x*mPercentX,points[11].y*mPercentY);
        path.lineTo(points[12].x*mPercentX,points[12].y*mPercentY);
        path.lineTo(points[13].x*mPercentX,points[13].y*mPercentY);
        path.lineTo(points[14].x*mPercentX,points[14].y*mPercentY);
        path.lineTo(points[15].x*mPercentX,points[15].y*mPercentY);
        path.lineTo(points[16].x*mPercentX,points[16].y*mPercentY);
        path.lineTo(points[17].x*mPercentX,points[17].y*mPercentY);
        path.lineTo(points[18].x*mPercentX,points[18].y*mPercentY);
        path.lineTo(points[19].x*mPercentX,points[19].y*mPercentY);
        path.lineTo(points[20].x*mPercentX,points[20].y*mPercentY);
        path.lineTo(points[21].x*mPercentX,points[21].y*mPercentY);
        path.lineTo(points[0].x*mPercentX,points[0].y*mPercentY);

        canvas.drawPath(path,paint);
    }
}
