###自定义View之贝塞尔曲线实战

都说”给我一个Path，我还你一个世界“
一直都被贝塞尔曲线面纱挡住没有勇敢的去揭开,看到别人玩的贝塞尔曲线 玩的那么嗨，现在是时候揭开那层面纱了
**效果:**
本案是模仿了：[贝塞尔曲线实战--BackgroundView](http://www.jianshu.com/p/1d766455445b) 并在该基础上润下色 核心原理都是一样的(可能有些雷同 望原创谅解)。
[demo]()

![](http://on96fbw9r.bkt.clouddn.com/a.gif)
###实现细节
1. 山 叶子 太阳 云 全部用Path Draw出来 没有使用一张图片
2. 动画采用 PathMeasure+ObjectAnimator实现

###贝塞尔曲线
1. 知识点(我反正是没怎么看懂)：

[贝塞尔曲线高级教程](https://pomax.github.io/bezierinfo/zh-CN/)
[贝塞尔曲线](http://blog.edreamoon.com/2015/12/27/%E8%B4%9D%E5%A1%9E%E5%B0%94%E6%9B%B2%E7%BA%BF/)
2. 工具使用：

- 对贝塞尔曲线那些数学公式是一脸懵圈的状态 天然性的抵触 包括很多人也是 
所以就有人想出其他办法来轻松实现贝塞尔曲线

[设计师教你怎么用最偷懒的方式画贝塞尔曲线](http://www.guokr.com/post/695800/focus/0060087686/)
[贝塞尔曲线在线绘制工具](http://www.jianshu.com/p/1d766455445b)

- 使用AI绘制

[教你如何使用钢笔工具](http://bezier.method.ac/)

以上面的云朵为例子：
>先用钢笔画出云朵的锥型

![](http://on96fbw9r.bkt.clouddn.com/cloud01.png)

> 润角（或者做角度调整）

![](http://on96fbw9r.bkt.clouddn.com/cloud02.png)

>打开标尺和网格 做坐标记录

![](http://on96fbw9r.bkt.clouddn.com/cloud03.png)

>估坐标值 并记录

![](http://on96fbw9r.bkt.clouddn.com/cloud04.png)
上图需要记录2组坐标值：**所有锚点坐标值** **所有手柄坐标值** 因为在Path api中需要这些数据 若对Path api不太熟悉的可以去了解这篇文章:

[Android自定义View之Path解析](http://www.2cto.com/kf/201604/503496.html)

```
//二次贝塞尔曲线（x1,y1）代表控制点 也就是上图的手柄坐标（x2,y2）代表结束点 也就是锚点坐标点
public void quadTo(float x1, float y1, float x2, float y2)
//三次贝塞尔曲线 （x1,y1）(x2,y2)代表控制点 也就是上图的手柄坐标(x3,y3)表结束点 也就是锚点坐标点
public void cubicTo(float x1, float y1, float x2, float y2, float x3, float y3)
```
> 通过上面记录的坐标值进行 绘制

```
.......
private void init() {
        .......
        for(int i=0;i<points.length;i++){
            points[i]=new PointF();
        }
        for(int i=0;i<controls.length;i++){
            controls[i]=new PointF();
        }
        points[0].set(18.5f,47.5f);
        points[1].set(39.5f,43.5f);
        points[2].set(67.5f,42.5f);
        ......
        controls[0].set(27,35);
        controls[1].set(34,34);
        controls[2].set(48,27);
        controls[3].set(59,27);
        ........
}
 @Override
    protected void onDraw(Canvas canvas) {
        Path path=new Path();
        path.moveTo(points[0].x,points[0].y*mPercentY);
        path.lineTo(points[1].x,points[1].y*mPercentY);
path.quadTo(controls[0].x*mPercentX,controls[0].y*mPercentY,points[2].x,points[2].y*mPercentY);
path.quadTo(controls[1].x*mPercentX,controls[1].y*mPercentY,points[3].x,points[3].y*mPercentY);
        path.lineTo(points[4].x,points[4].y*mPercentY);
        path.lineTo(points[0].x,points[0].y*mPercentY);
        canvas.drawPath(path,paint);
        }
```
>叶子漂移动画是 PathMeasure+ObjectAnimator实现的

 - 首先叶子的漂移轨迹也是贝塞尔曲线  可以通过AI制作好路线（当然 你想怎么飘就怎么飘）

![](http://on96fbw9r.bkt.clouddn.com/cloud06.png)

- 记录好 **所有锚点坐标值** **所有手柄坐标值** 使用Path绘制贝塞尔曲线

```
        Path path = new Path();
        path.moveTo(mData[0].x, mData[0].y * mScaleH);
        path.quadTo(mCtrl[0].x * mScaleW, mCtrl[0].y * mScaleH, mData[1].x * mScaleW, mData[1].y * mScaleH);
        path.cubicTo(mCtrl[1].x * mScaleW, mCtrl[1].y * mScaleH, mCtrl[2].x * mScaleW, mCtrl[2].y * mScaleH,
                mData[2].x * mScaleW, mData[2].y * mScaleH);
        path.cubicTo(mCtrl[3].x * mScaleW, mCtrl[3].y * mScaleH, mCtrl[4].x * mScaleW, mCtrl[4].y * mScaleH, mData[3]
                .x * mScaleW, mData[3].y * mScaleH);
        path.quadTo(mCtrl[5].x * mScaleW, mCtrl[5].y * mScaleH, mData[4].x * mScaleW, mData[4].y * mScaleH);
```
- 开启动画

```
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
```

>PathMeasure.getLength(value, pointF, null) 通过属性动画的animation.getAnimatedValue();值去获取贝塞尔曲线具体某一点的坐标 封装到float[] pointF = new float[2];数组中 最后通过view.setTranslationY(pointF[1]);的方式去显示View的动画效果

####最后
对于如何使用Path去绘制贝塞尔曲线或者如何去判断是使用二次贝塞尔曲线还是三次贝塞尔曲线这些都需要自己去实际经验中去不断的总结，多敲代码，一切就都明朗起来。



