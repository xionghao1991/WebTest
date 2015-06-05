package kokist.android.webtest.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import java.util.ArrayList;
import java.util.List;

import kokist.android.webtest.R;

/**
 * Created by xiong on 2015/6/4.
 */
public class Splash extends View {

    //大圆半径
    private float mRotationRadius = 240f;
    //小圆的半径
    private float mCrileRadius = 40f;
    //小圆的颜色
    private int[] colors;
    //大圆和小圆转的时间
    private long mRotaionDuration = 1200;
    //
    private long SplashTime = 1200;


    /***
     * 下面的参数都是会动态改变的参数
     */
    //空心圆的半径
    private float mHoloRadius = 0f;
    //当前的角度
    private float mCurrtiAngle = 0f;
    //当前的大园的半径
    private float mCurrtRadius = mRotationRadius;
    //画笔背景颜色
    private int mbackgroudcolor = Color.WHITE;
    //绘制背景的画笔
    private Paint bgpaint = new Paint();
    //
    private Paint paint = new Paint();

    //中心点的x坐标
    private float centerx;
    //中心点的y坐标
    private float centery;

    //屏幕中心点的一半
    private float mScreenWith;

    private SplashState mstate = null;

    public interface onAnimatiorLister{
        public void  onAnimatorEnd(Splash splash);
    }

    public Splash(Context context) {
        super(context);
        init(context);

    }

    private void init(Context context) {
        colors = context.getResources().getIntArray(R.array.cirle_colors);
        //画笔初始化

        paint.setAntiAlias(true);
        bgpaint.setAntiAlias(true);
        bgpaint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerx = w / 2f;
        centery = h / 2f;
        mScreenWith = (float) (Math.sqrt(w * w + h * h) / 2f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制动画
        if (mstate == null) {
            //绘制小球旋转动画
            mstate = new RotateState();

        }
        mstate.drawState(canvas);
        super.onDraw(canvas);
    }

    //数据加载完毕之后加载后面的两个动画
    public void splashAndDisMiss() {

        if (mstate != null && mstate instanceof RotateState) {

            RotateState s = (RotateState) mstate;
            s.cancelRotation();
            post(new Runnable() {
                @Override
                public void run() {
                    mstate = new MergingState();
                }
            });
        }

    }

    /***
     * 动画状态的抽象类
     */
    private abstract class SplashState {
        public abstract void drawState(Canvas canvas);
    }

    private class RotateState extends SplashState {

        private final ValueAnimator manimator;

        public RotateState() {
            //绘制小圆，得到小圆的坐标，控制坐标变换，形成动画

            //小圆坐标->大圆半径，大圆旋转了多少度
            manimator = ValueAnimator.ofFloat(0, (float) Math.PI * 2);
            manimator.setDuration(mRotaionDuration);
            manimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mCurrtiAngle = (float) animation.getAnimatedValue();
                    //重绘view
                    invalidate();
                }
            });
            //设置线性插值器，使值平滑过度
            manimator.setInterpolator(new LinearInterpolator());
            manimator.setRepeatCount(ValueAnimator.INFINITE);//设置动画重复次数为无穷大
//            manimator.setRepeatMode(ValueAnimator.REVERSE);
            manimator.start();


        }

        @Override
        public void drawState(Canvas canvas) {
            //实现动画的绘制
            //先将画板清空
            drawblackgroud(canvas);

            drawcricle(canvas);


        }

        public void cancelRotation() {
            manimator.cancel();
        }
    }

    private void drawcricle(Canvas canvas) {
        //根据坐标绘制小圆
        //得到小圆之间的间隔角度

        float rotationAngle = (float) (2 * Math.PI / colors.length);
        for (int i = 0; i < colors.length; i++) {

            double angle = mCurrtiAngle + i * rotationAngle;

            float cx = (float) (mCurrtRadius * Math.cos(angle) + centerx);
            float cy = (float) (mCurrtRadius * Math.sin(angle) + centery);
            paint.setColor(colors[i]);
            canvas.drawCircle(cx, cy, mCrileRadius, paint);
        }

    }

    private void drawblackgroud(Canvas canvas) {

        if (mHoloRadius > 0) {
            /***
             * 使用非常宽的画笔
             *
             */
            //画笔的宽度=对角线/2-空心部分的半径
            float strokeWidth=mScreenWith-mHoloRadius;
            bgpaint.setStrokeWidth(strokeWidth);
            bgpaint.setColor(mbackgroudcolor);
            float cirleRandius=mHoloRadius+strokeWidth/2;

            canvas.drawCircle(centerx,centery,cirleRandius,bgpaint);


        } else {
            canvas.drawColor(mbackgroudcolor);
        }

    }

    private List<onAnimatiorLister> listers;

    private class MergingState extends SplashState {

        private final ValueAnimator manimator;

        public MergingState() {
            manimator = ValueAnimator.ofFloat(0, mRotationRadius);
            manimator.setDuration(SplashTime / 3);
            manimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mCurrtRadius = (float) animation.getAnimatedValue();
                    //重绘view
                    invalidate();
                }
            });
            //设置线性插值器，使值平滑过度
            manimator.setInterpolator(new OvershootInterpolator(6f));
            manimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    //进入下一个动画
                    mstate=new ExplandingState();
                }
            });
//            manimator.setRepeatMode(ValueAnimator.REVERSE);
            manimator.reverse();//反转开始动画

        }

        @Override
        public void drawState(Canvas canvas) {
            drawblackgroud(canvas);
            drawcricle(canvas);
        }
    }

    private class ExplandingState extends SplashState implements onAnimatiorLister {



        private final ValueAnimator manimator;


        public ExplandingState() {
            //估值器（空心圆的半径 0-对角线的一半）
            manimator = ValueAnimator.ofFloat(0, mScreenWith);
            manimator.setDuration(SplashTime / 3);
            manimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mHoloRadius = (float) animation.getAnimatedValue();
                    //重绘view
                    invalidate();
                }
            });
            //设置线性插值器，使值平滑过度
//            manimator.setInterpolator(new OvershootInterpolator(6f));
            manimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    //结束动画监听移除自身
                    if (listers!=null){
                        for (int i = 0; i < listers.size(); i++) {
                            listers.get(i).onAnimatorEnd(Splash.this);
                        }
                    }
                }
            });
//            manimator.setRepeatMode(ValueAnimator.REVERSE);
            manimator.start();


        }

        @Override
        public void drawState(Canvas canvas) {
            //实现动画
            //清空画板,绘制空心圆
            drawblackgroud(canvas);
        }

        @Override
        public void onAnimatorEnd(Splash splash) {

        }
    }

    public void setOnAnimatorLister(onAnimatiorLister lister){
         if (listers!=null){
             listers.add(lister);
         }else {
             listers=new ArrayList<onAnimatiorLister>();
             listers.add(lister);
         }
    }


}
