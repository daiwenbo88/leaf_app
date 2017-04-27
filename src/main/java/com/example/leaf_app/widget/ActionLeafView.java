package com.example.leaf_app.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;


import com.example.leaf_app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author : daiwenbo
 * e-mail : daiwwenb@163.com
 * date   : 2017/4/26
 * description   : xxxx描述
 */

public class ActionLeafView extends RelativeLayout {
    private PointF[] mData = new PointF[5];  //5个数据点
    private PointF[] mCtrl = new PointF[6]; //6个控制点
    private AbLeafView mLeaf1, mLeaf2, mLeaf3, mLeaf4, mLeaf5, mLeaf6, mLeaf7, mLeaf8;
    private AbCloudView cloud, cloud01, cloud02, cloud03;
    private List<Animator> mAnimators = new ArrayList<>();

    public ActionLeafView(Context context) {
        this(context, null);
    }

    public ActionLeafView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ActionLeafView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ActionLeafView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_main, this, true);
        mLeaf1 = (AbLeafView) findViewById(R.id.leaf1);
        mLeaf2 = (AbLeafView) findViewById(R.id.leaf2);
        mLeaf3 = (AbLeafView) findViewById(R.id.leaf3);
        mLeaf4 = (AbLeafView) findViewById(R.id.leaf4);
        mLeaf5 = (AbLeafView) findViewById(R.id.leaf5);
        mLeaf6 = (AbLeafView) findViewById(R.id.leaf6);
        mLeaf7 = (AbLeafView) findViewById(R.id.leaf7);
        mLeaf8 = (AbLeafView) findViewById(R.id.leaf8);

        cloud = (AbCloudView) findViewById(R.id.cloud);
        cloud01 = (AbCloudView) findViewById(R.id.cloud01);
        cloud02 = (AbCloudView) findViewById(R.id.cloud02);
        cloud03 = (AbCloudView) findViewById(R.id.cloud03);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initBezierCurve(w, h);
        mAnimators.add(startLeafAnimation(mLeaf1, w, 200, 6000, 0, new AccelerateInterpolator()));
        mAnimators.add(startLeafAnimation(mLeaf2, w, 200, 5000, 3000, new AccelerateDecelerateInterpolator()));
        mAnimators.add(startLeafAnimation(mLeaf3, w, 200, 6000, 2000, new OvershootInterpolator()));
        mAnimators.add(startLeafAnimation(mLeaf4, w, 200, 5000, 5000, new AccelerateInterpolator()));
        mAnimators.add(startLeafAnimation(mLeaf5, w, 200, 6000, 1000, new AccelerateDecelerateInterpolator()));
        mAnimators.add(startLeafAnimation(mLeaf6, w, 200, 5000, 4000, new DecelerateInterpolator()));
        mAnimators.add(startLeafAnimation(mLeaf7, w, 200, 5000, 2500, new AccelerateDecelerateInterpolator()));
        mAnimators.add(startLeafAnimation(mLeaf8, w, 200, 5000, 4000, new DecelerateInterpolator()));

        mAnimators.add(startCloudAnimator(cloud, w, 70000, 3000));
        mAnimators.add(startCloudAnimator(cloud01, w, 50000, 2000));
        mAnimators.add(startCloudAnimator(cloud02, w, 60000, 2000));
        mAnimators.add(startCloudAnimator(cloud03, w, 70000, 6000));

    }

    private void initBezierCurve(int w, int h) {
        /*for (int i = 0; i < mData.length; i++) {
            mData[i] = new PointF();
        }
        for (int i = 0; i < mCtrl.length; i++) {
            mCtrl[i] = new PointF();
        }
        mData[0].set(20, 5.5f);
        mData[1].set(7.5f, 51.5f);
        mData[2].set(2, 120);

        mCtrl[0].set(10, 40);
        mCtrl[1].set(5, 63);*/
        for (int i = 0; i < mData.length; i++) {
            mData[i] = new PointF();
        }
        for (int i = 0; i < mCtrl.length; i++) {
            mCtrl[i] = new PointF();
        }

        mData[0].set(w, 10);
        mData[1].set(81, 9);
        mData[2].set(54, 9);
        mData[3].set(27, 9);
        mData[4].set(-20, 10);

        mCtrl[0].set(90, 5);
        mCtrl[1].set(72, 13.5f);
        mCtrl[2].set(59, 12);
        mCtrl[3].set(49, 6);
        mCtrl[4].set(37, 5);
        mCtrl[5].set(16.5f, 14);

    }


    /**
     * 开启叶子动画
     *
     * @param view         哪一个叶子
     * @param width        比例
     * @param height       比例
     * @param duration     动画持续时间
     * @param startDelay   动画延迟开始时间
     * @param interpolator 动画差值器
     * @return AnimatorSet
     */
    private AnimatorSet startLeafAnimation(final View view, int width, int height, long duration, long startDelay,
                                           Interpolator interpolator) {
        /**
         * 缩放比例
         */
        float mScaleW, mScaleH;

        mScaleW = width / 100;
        mScaleH = height / 20;

        Path path = new Path();
        path.moveTo(mData[0].x, mData[0].y * mScaleH);
        path.quadTo(mCtrl[0].x * mScaleW, mCtrl[0].y * mScaleH, mData[1].x * mScaleW, mData[1].y * mScaleH);
        path.cubicTo(mCtrl[1].x * mScaleW, mCtrl[1].y * mScaleH, mCtrl[2].x * mScaleW, mCtrl[2].y * mScaleH,
                mData[2].x * mScaleW, mData[2].y * mScaleH);
        path.cubicTo(mCtrl[3].x * mScaleW, mCtrl[3].y * mScaleH, mCtrl[4].x * mScaleW, mCtrl[4].y * mScaleH, mData[3]
                .x * mScaleW, mData[3].y * mScaleH);
        path.quadTo(mCtrl[5].x * mScaleW, mCtrl[5].y * mScaleH, mData[4].x * mScaleW, mData[4].y * mScaleH);

        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 0, 360);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.INFINITE);
        animator.setDuration(duration);

        final PathMeasure measure = new PathMeasure(path, false);
        final float[] pointF = new float[2];
        ValueAnimator viewAnimator = ValueAnimator.ofFloat(0, measure.getLength());
        viewAnimator.setDuration(duration);
        viewAnimator.setStartDelay(startDelay);
        viewAnimator.setRepeatCount(ValueAnimator.INFINITE);
        viewAnimator.setRepeatMode(ValueAnimator.INFINITE);
        viewAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                // 获取当前点坐标封装到pointF
                measure.getPosTan(value, pointF, null);
                view.setX(pointF[0]);
                view.setTranslationY(pointF[1]);
            }
        });
        viewAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(INVISIBLE);
            }

        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(viewAnimator, animator);
        animatorSet.start();
        return animatorSet;
    }

    private ObjectAnimator startCloudAnimator(final View view, int width, long duration, long startDelay) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "x", width, -width/2);
        animator.setDuration(duration);
        animator.setStartDelay(startDelay);
        animator.setRepeatMode(ValueAnimator.INFINITE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(INVISIBLE);

            }

            @Override
            public void onAnimationStart(Animator animation) {
                view.setVisibility(VISIBLE);
            }
        });
        animator.start();
        return animator;
    }

}
