package com.example.leaf_app.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
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

public abstract class AbLeafView extends View {
    protected int leaf_color;
    protected float mPercentX, mPercentY;//比例控制
    protected int default_color= Color.parseColor("#888888");
    protected Paint paint;
    public AbLeafView(Context context) {
        this(context,null);
    }

    public AbLeafView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AbLeafView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.LeafColor);
        leaf_color=typedArray.getColor(R.styleable.LeafColor_android_color, ContextCompat.getColor(context,R.color.leafColor));
        typedArray.recycle();
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AbLeafView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray  typedArray=context.obtainStyledAttributes(attrs, R.styleable.LeafColor);
        leaf_color=typedArray.getColor(R.styleable.LeafColor_android_color, ContextCompat.getColor(context,R.color.leafColor));
        typedArray.recycle();
        init();
    }

    private void init() {
        paint=new Paint();
        paint.setAntiAlias(true);//抗锯齿
        paint.setDither(true);//柔和
        paint.setColor(leaf_color);
        paint.setStrokeWidth(8);
        paint.setStyle(Paint.Style.FILL);//充满
        paint.setPathEffect(new CornerPathEffect(4));//曲线圆角度
        createInit();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        setData(w,h);
        super.onSizeChanged(w, h, oldw, oldh);

    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //然后调用setMeasuredDimension(int, int)设置实际大小
        setMeasuredDimension(measure(widthMeasureSpec),measure(heightMeasureSpec));
    }
    //获取高度和宽度
    private int measure(int heightMeasureSpec) {
        int result=0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);//模式
        int size = MeasureSpec.getSize(heightMeasureSpec);//尺寸
        if(mode==MeasureSpec.EXACTLY){//精确尺寸
            result=size;
        }else {
            result=200;
            if(mode==MeasureSpec.AT_MOST){//指定为WRAP_CONTENT时
                result=Math.min(result,size);//取最小
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        leafDraw(canvas);
    }

    protected abstract void createInit();
    protected abstract void setData(int w, int h);
    protected abstract void leafDraw(Canvas canvas);
}
