package com.example.leaf_app.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
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

public class MountainView extends View {
    private static final int STYLE_1 = 0;
    private static final int STYLE_2 = 1;
    private static final int STYLE_3 = 2;
    private float mScaleW, mScaleH;
    private int mWidth, mHeight;
    private int mountaincolor;
    private int mountaincolor02;
    private int mountaincolor03;
    private int mStyle;
    private Paint mPaint;
    private Paint mPaint02;
    private Paint mPaint03;
    private PointF[] mPoint = new PointF[16];  //5个数据点
    private PointF[] mCtrl = new PointF[20]; //7个控制点
    public MountainView(Context context) {
        this(context,null);
    }

    public MountainView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MountainView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.mountainStyle);
        mountaincolor = array.getColor(R.styleable.mountainStyle_android_color, ContextCompat.getColor(context,R.color.mountainColor_01));
        mStyle=array.getInteger(R.styleable.mountainStyle_style,0);
        mountaincolor02=ContextCompat.getColor(context,R.color.mountainColor_02);
        mountaincolor03=ContextCompat.getColor(context,R.color.mountainColor_03);
        array.recycle();
        init();
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MountainView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.mountainStyle);
        mountaincolor = array.getColor(R.styleable.mountainStyle_android_color, ContextCompat.getColor(context,R.color.mountainColor_01));
        mStyle=array.getInteger(R.styleable.mountainStyle_style,0);
        mountaincolor02=ContextCompat.getColor(context,R.color.mountainColor_02);
        mountaincolor03=ContextCompat.getColor(context,R.color.mountainColor_03);
        array.recycle();
        init();
    }
    private void init() {
        mPaint = new Paint();
        // 绘制贝塞尔曲线
        mPaint.setColor(mountaincolor);
        mPaint.setStrokeWidth(8);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setPathEffect(new CornerPathEffect(4));
        mPaint02 = new Paint();
        // 绘制贝塞尔曲线
        mPaint02.setColor(mountaincolor02);
        mPaint02.setStrokeWidth(8);
        mPaint02.setAntiAlias(true);
        mPaint02.setDither(true);
        mPaint02.setStyle(Paint.Style.FILL);
        mPaint02.setPathEffect(new CornerPathEffect(4));
        mPaint03 = new Paint();
        // 绘制贝塞尔曲线
        mPaint03.setColor(mountaincolor03);
        mPaint03.setStrokeWidth(8);
        mPaint03.setAntiAlias(true);
        mPaint03.setDither(true);
        mPaint03.setStyle(Paint.Style.FILL);
        mPaint03.setPathEffect(new CornerPathEffect(4));

        for (int i = 0; i < mPoint.length; i++) {
            mPoint[i] = new PointF();
        }
        for (int i = 0; i < mCtrl.length; i++) {
            mCtrl[i] = new PointF();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth=w;
        mHeight=h;
        mScaleW=w/120;
        mScaleH=h/40;
        super.onSizeChanged(w, h, oldw, oldh);
        switch (mStyle){
            case STYLE_1:

                mPoint[0].set(0,30);
                mPoint[1].set(22.5f,19.5f);
                mPoint[2].set(45.5f,33.5f);
                mPoint[3].set(77.5f,17);
                mPoint[4].set(106.5f,28.5f);
                mPoint[5].set(mWidth,23);

                mPoint[6].set(0,17.5f);
                mPoint[7].set(27.5f,8.5f);
                mPoint[8].set(57.5f,16.5f);
                mPoint[9].set(106.5f,7.5f);
                mPoint[10].set(mWidth,14);

                mPoint[11].set(37.5f,9.5f);
                mPoint[12].set(55.5f,4.5f);
                mPoint[13].set(76.5f,7.5f);
                mPoint[14].set(102.5f,1.5f);
                mPoint[15].set(mWidth,3);




                mCtrl[0].set(16,19);
                mCtrl[1].set(33.5f,19.5f);
                mCtrl[2].set(39,33);
                mCtrl[3].set(55,33.5f);
                mCtrl[4].set(69,17);
                mCtrl[5].set(85.5f,17);
                mCtrl[6].set(99,28);
                mCtrl[7].set(110,29);

                mCtrl[8].set(20,9);
                mCtrl[9].set(37,8);
                mCtrl[10].set(48,16);
                mCtrl[11].set(81,17);
                mCtrl[12].set(102.5f,7.5f);
                mCtrl[13].set(111.5f,7.5f);

                mCtrl[14].set(51,5);
                mCtrl[15].set(61,4);
                mCtrl[16].set(71,7.5f);
                mCtrl[17].set(83,7);
                mCtrl[18].set(96,2);
                mCtrl[19].set(102,1);
                break;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //然后调用setMeasuredDimension(int, int)设置实际大小
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));
    }
    //获取宽度
    private int measureWidth(int heightMeasureSpec) {
        int result=0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);//模式
        int size = MeasureSpec.getSize(heightMeasureSpec);//尺寸
        if(mode==MeasureSpec.EXACTLY){//精确尺寸
            result=size;
        }else {
            result=mWidth;
            if(mode==MeasureSpec.AT_MOST){//指定为WRAP_CONTENT时
                result=Math.min(result,size);//取最小
            }
        }
        return result;
    }
    //获取高度
    private int measureHeight(int heightMeasureSpec) {
        int result=0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);//模式
        int size = MeasureSpec.getSize(heightMeasureSpec);//尺寸
        if(mode==MeasureSpec.EXACTLY){//精确尺寸
            result=size;
        }else {
            result=100;
            if(mode==MeasureSpec.AT_MOST){//指定为WRAP_CONTENT时
                result=Math.min(result,size);//取最小
            }
        }
        return result;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        path.moveTo(mPoint[11].x*mScaleW,mPoint[11].y*mScaleH);
        path.quadTo(mCtrl[14].x*mScaleW,mCtrl[14].y*mScaleH,mPoint[12].x*mScaleW,mPoint[12].y*mScaleH);
        path.cubicTo(mCtrl[15].x*mScaleW,mCtrl[15].y*mScaleH,mCtrl[16].x*mScaleW,mCtrl[16].y*mScaleH,mPoint[13].x*mScaleW,mPoint[13].y*mScaleH);
        path.cubicTo(mCtrl[17].x*mScaleW,mCtrl[17].y*mScaleH,mCtrl[18].x*mScaleW,mCtrl[18].y*mScaleH,mPoint[14].x*mScaleW,mPoint[14].y*mScaleH);
        path.quadTo(mCtrl[19].x*mScaleW,mCtrl[19].y*mScaleH,mWidth,mPoint[15].y*mScaleH);
        path.lineTo(mWidth,mHeight);
        canvas.drawPath(path,mPaint03);

        path = new Path();
        path.moveTo(0,mHeight);
        path.lineTo(mPoint[6].x*mScaleW,mPoint[6].y*mScaleH);
        path.quadTo(mCtrl[8].x*mScaleW,mCtrl[8].y*mScaleH,mPoint[7].x*mScaleW,mPoint[7].y*mScaleH);
        path.cubicTo(mCtrl[9].x*mScaleW,mCtrl[9].y*mScaleH,mCtrl[10].x*mScaleW,mCtrl[10].y*mScaleH,mPoint[8].x*mScaleW,mPoint[8].y*mScaleH);
        path.cubicTo(mCtrl[11].x*mScaleW,mCtrl[11].y*mScaleH,mCtrl[12].x*mScaleW,mCtrl[12].y*mScaleH,mPoint[9].x*mScaleW,mPoint[9].y*mScaleH);
        path.quadTo(mCtrl[13].x*mScaleW,mCtrl[13].y*mScaleH,mWidth,mPoint[10].y*mScaleH);
        path.lineTo(mWidth,mHeight);
        canvas.drawPath(path,mPaint);

        path = new Path();
        path.moveTo(0,mHeight);
        path.lineTo(mPoint[0].x*mScaleW,mPoint[0].y*mScaleH);
        path.quadTo(mCtrl[0].x*mScaleW,mCtrl[0].y*mScaleH,mPoint[1].x*mScaleW,mPoint[1].y*mScaleH);
        path.cubicTo(mCtrl[1].x*mScaleW,mCtrl[1].y*mScaleH,mCtrl[2].x*mScaleW,mCtrl[2].y*mScaleH,mPoint[2].x*mScaleW,mPoint[2].y*mScaleH);
        path.cubicTo(mCtrl[3].x*mScaleW,mCtrl[3].y*mScaleH,mCtrl[4].x*mScaleW,mCtrl[4].y*mScaleH,mPoint[3].x*mScaleW,mPoint[3].y*mScaleH);
        path.cubicTo(mCtrl[5].x*mScaleW,mCtrl[5].y*mScaleH,mCtrl[6].x*mScaleW,mCtrl[6].y*mScaleH,mPoint[4].x*mScaleW,mPoint[4].y*mScaleH);
        path.quadTo(mCtrl[7].x*mScaleW,mCtrl[7].y*mScaleH,mPoint[5].x*mScaleW,mPoint[5].y*mScaleH);
        path.lineTo(mWidth,mHeight);
        path.lineTo(0,mHeight);
        canvas.drawPath(path,mPaint02);
    }

}
