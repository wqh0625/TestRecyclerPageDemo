package com.wqh.livedatabug.textchangecolor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.wqh.livedatabug.R;
import com.wqh.livedatabug.utils.LogUtils;

/**
 * 作者: Wang on 2019/11/24 14:29
 * 寄语：加油！相信自己可以！！！
 */


public class ChangeTextColorView extends View {

    private float mTextSize;
    private String mTextValue;
    private Paint mPaint;
    private Rect mBound=null;
//    private Paint paint;
//    private Canvas crateBitmapCanvas;
//    private Canvas crateDestCanvas;
//    private Paint crateBitmapPaint;
//    private Paint crateDestPaint;
    private PorterDuffXfermode porterDuffXfermode;
    private int mRectWidth = 20;
    public ChangeTextColorView(Context context) {
        this(context,null);
    }

    public ChangeTextColorView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ChangeTextColorView(Context context, AttributeSet attrs, int i) {
         super(context);
        init();

        initAttriButeSet(context, attrs);

    }


    private void init() {

//        paint = new Paint();
//        crateBitmapCanvas = new Canvas();
//        crateDestCanvas = new Canvas();
//        crateBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        crateDestPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }

    private void initAttriButeSet(Context context, AttributeSet attributeSet) {
        // 获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ChangeTextColor);
        mTextValue = typedArray.getString(R.styleable.ChangeTextColor_text_value);
        mTextSize = typedArray.getDimension(R.styleable.ChangeTextColor_text_size, 16);
        // 回收TypeArray
        typedArray.recycle();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 设置字体大小
        mPaint.setTextSize(mTextSize);

        mBound = new Rect();
        LogUtils.w(String.valueOf("高" +mBound!=null));
        LogUtils.w(String.valueOf("高" +mBound.height()));
        // 用来计算当前mTextValue的宽度和高度
        mPaint.getTextBounds(mTextValue, 0, mTextValue.length(), mBound);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthsize = MeasureSpec.getSize(widthMeasureSpec);
        int heightsize = MeasureSpec.getSize(heightMeasureSpec);
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);
        int heightmode = MeasureSpec.getMode(heightMeasureSpec);

        int finalWidth = 0;
        int finalHeight = 0;

        if (MeasureSpec.EXACTLY == widthmode) {
            // 如果测量模式 是EXACTLY  则直接使用推荐值加上padding的值
            finalWidth = widthsize + getPaddingLeft() + getPaddingRight();
        }else{
            finalWidth = getPaddingLeft() + getPaddingRight() + mBound.width();
            if (MeasureSpec.AT_MOST == widthmode) {
                // 如果测量模式是 AT_MOST 则取期望值和我们计算出的最小值
                finalWidth = Math.min(finalWidth,widthsize);
            }
        }

        // 高
        if (heightmode == MeasureSpec.EXACTLY) {
            finalHeight = heightsize + getPaddingLeft() + getPaddingRight();
        }else{
            finalHeight = getPaddingLeft() + getPaddingRight() + mBound.height();
            if (heightmode == MeasureSpec.AT_MOST) {
                finalHeight = Math.min(finalHeight,heightsize);
            }
        }

        // 保存
        setMeasuredDimension(finalWidth,finalHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        //创建一个图层，在图层上演示图形混合后的效果
        @SuppressLint("WrongConstant")
        int sc = canvas.saveLayer(0,0,width,height ,null,Canvas.ALL_SAVE_FLAG);

        Paint paint = new Paint();

        // 先绘制 dest
        canvas.drawBitmap(getDestBitmap(width,height),0,0,paint);

        Log.v("谁打得过谁的故事",""+mBound);
        // 设置Paint的xfermode
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        mRectWidth += 10;
        canvas.drawBitmap(getSrcBitmap(mRectWidth,height),0,0,paint);
        // 重置当前xfermode
        paint.setXfermode(null);
        // 还原画布
        canvas.restoreToCount(sc);

        if (mRectWidth < width) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            invalidate();
        }
    }

    // 创建一个矩形bitmap，作为src的bitmap
    private Bitmap getSrcBitmap(int w,int h){
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas crateBitmapCanvas = new Canvas( bitmap);

        // 绘制边框
        Paint crateBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        crateBitmapPaint.setStrokeWidth(3);
        crateBitmapPaint.setColor(Color.RED);
        crateBitmapPaint.setStyle(Paint.Style.STROKE);
        crateBitmapCanvas.drawRect(0,0,w,h,crateBitmapPaint);

        // 绘制文字
        crateBitmapPaint.setColor(Color.BLACK);
        crateBitmapPaint.setStyle(Paint.Style.FILL);
        crateBitmapPaint.setTextSize(mTextSize);
        crateBitmapCanvas.drawText(mTextValue,w/2-mBound.width()/2,h/2+mBound.height(),crateBitmapPaint);
        return bitmap;
    }

    private Bitmap getDestBitmap(int w,int h){
        // 创建一个位图
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        // 创建一个和当前位图相同大小的canvas对象
        Canvas crateDestCanvas = new Canvas(bitmap);


        Paint crateDestPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        crateDestPaint.setColor(Color.GREEN);
        crateDestCanvas.drawRect(0,0,w,h,crateDestPaint);
        return bitmap;
    }
}
