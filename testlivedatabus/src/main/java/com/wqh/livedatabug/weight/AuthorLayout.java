package com.wqh.livedatabug.weight;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.wqh.livedatabug.R;

/**
 * 条形进度条（可设置 线性渐变-背景色-进度条颜色-进度条高度）
 * Created by ZWQ on 2018/6/29.
 */
public class AuthorLayout extends View {

    public static final float MAX = 100f;
    public static final int RADIUS = 15;     // 圆角矩形半径
    private RectF rectFBg;
    private RectF rectFProgress;
    private Paint mPaint;
    private int mWidth;
    private float progressPercent;
    private int bgColor, progressColor;
    private int mHeight;
    private int radius;
    private int startColor, endColor;
    private LinearGradient gradient;
    private boolean isGradient;

    public AuthorLayout(Context context) {
        super(context);
        init();
    }

    public AuthorLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BarPercentView);
        bgColor = typedArray.getColor(R.styleable.BarPercentView_barBgColor, getResources().getColor(R.color.ccc));
        progressColor = typedArray.getColor(R.styleable.BarPercentView_barProgressColor, getResources().getColor(R.color.blue));
        isGradient = typedArray.getBoolean(R.styleable.BarPercentView_barIsGradient, false);
        startColor = typedArray.getColor(R.styleable.BarPercentView_barStartColor, getResources().getColor(R.color.colorAccent));
        endColor = typedArray.getColor(R.styleable.BarPercentView_barEndColor, getResources().getColor(R.color.colorAccent));
        radius = typedArray.getInt(R.styleable.BarPercentView_barRadius, RADIUS);
        init();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        mHeight = getHeight();
        mWidth = getWidth();
    }

    public AuthorLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        gradient = new LinearGradient(0, 0, getWidth(), mHeight, startColor, endColor, Shader.TileMode.CLAMP);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //1、背景
        mPaint.setShader(null);
        mPaint.setColor(bgColor);
        rectFBg.right = mWidth; //宽度
        rectFBg.bottom = mHeight; //高度
        canvas.drawRoundRect(rectFBg, radius, radius, mPaint);
        //2、进度条
        rectFProgress.right = mWidth * progressPercent;
        rectFProgress.bottom = mHeight;
        //3、是否绘制渐变色
        if (isGradient) {
            mPaint.setShader(gradient);//设置线性渐变
        } else {
            mPaint.setColor(progressColor);
        }
        if (progressPercent > 0 && rectFProgress.right < radius)//进度值小于半径时，设置大于半径的最小值，防止绘制不出圆弧矩形
            rectFProgress.right = radius + 10;
        canvas.drawRoundRect(rectFProgress, radius, radius, mPaint);//进度}
    }

    @Keep
    public void setProgress(float percentage) {
        if (percentage / MAX >= 1) {
            this.progressPercent = 1;
        } else {
            this.progressPercent = percentage;
            invalidate();
//            @SuppressLint("ObjectAnimatorBinding") ValueAnimator valueAnimator = ObjectAnimator.ofFloat( 0, percentage);
//            valueAnimator.setDuration(1200);
//            valueAnimator.start();
//            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                @Override
//                public void onAnimationUpdate(ValueAnimator animation) {
//                    progressPercent = (float) animation.getAnimatedValue();
//                    Log.v("","WQH:动画值"+animation.getAnimatedValue());
//                    invalidate();
//                }
//            });
        }

    }

    private void init() {
        rectFBg = new RectF(0, 0, 0, mHeight);
        rectFProgress = new RectF(0, 0, 0, mHeight);
        mPaint = new Paint();
        //设置抗锯齿
        mPaint.setAntiAlias(true);
    }

    public void setHeight(int mHeight) {
        this.mHeight = mHeight;
        invalidate();
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
    }

    public void setStartColor(int startColor) {
        this.startColor = startColor;
    }

    public void setEndColor(int endColor) {
        this.endColor = endColor;
    }

    public void setGradient(boolean gradient) {
        isGradient = gradient;
    }
}
