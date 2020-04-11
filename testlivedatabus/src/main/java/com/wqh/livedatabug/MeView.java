package com.wqh.livedatabug;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者: Wang on 2019/11/15 22:09
 * 寄语：加油！相信自己可以！！！
 */


public class MeView extends View {

    private Paint mPaint;
    private Context context;
    private Path path;

    public MeView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public MeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public MeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    @SuppressLint("ResourceAsColor")
    private void init(){
        mPaint = new Paint();
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(context.getResources().getColor(R.color.blue));
        mPaint.setStrokeWidth(5);
        canvas.drawCircle(100,100,50,mPaint);
        canvas.drawText("王庆浩",100,50,mPaint);

    }
}
