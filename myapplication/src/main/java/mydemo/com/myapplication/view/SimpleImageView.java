package mydemo.com.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import mydemo.com.myapplication.R;


/**
 * 作者: Wang on 2020/4/11 16:22
 * 寄语：加油！相信自己可以！！！
 *
 * @author Wang
 */


public class SimpleImageView extends View {

    // 画笔
    private Paint mBitmapPaint;
    // 图片 drawable
    private Drawable mDrawable;
    // 图片 bitmap
    private Bitmap mBitmap;
    // View的宽度
    private int mWidth;
    // View 的高度
    private int mHeight;

    public SimpleImageView(Context context) {
        super(context);
    }

    public SimpleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);
    }

    public SimpleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);
    }

    private void initAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = null;
            try {
                array = getContext().obtainStyledAttributes(attrs, R.styleable.SimpleImageView);
                // 根据图片Id 获取到Drawable对象
                mDrawable = array.getDrawable(R.styleable.SimpleImageView_src);
                // 测量Drawable对象的宽高
                measureDrawable();
            } catch (Exception e) {

            } finally {
                if (array != null) {
                    array.recycle();
                }
            }
        }
    }

    private void measureDrawable() {
        if (mDrawable != null) {
            mHeight = mDrawable.getIntrinsicHeight();
            mWidth = mDrawable.getIntrinsicWidth();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec,heightMeasureSpec);

        // 宽度的模式和大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        // 噶度的模式和大小
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        setMeasuredDimension(measureWidth(widthMode, widthSize), measureHeight(heightMode, heightSize));
    }

    private int measureWidth(int widthMode, int widthSize) {
        switch (widthMode) {
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.EXACTLY:
                mWidth = widthSize;
                break;
            default:
                break;
        }
        return mWidth;
    }

    private int measureHeight(int heightMode, int heightSize) {
        switch (heightMode) {
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.EXACTLY:
                mHeight = heightSize;
                break;
            default:
                break;
        }
        return mHeight;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mBitmap == null) {
            mBitmap = Bitmap.createScaledBitmap(
                    convertDrawable2Bitmap(mDrawable),getMeasuredWidth(),getMeasuredHeight(),true);
        }

        // 绘制图片
        canvas.drawBitmap(mBitmap, getLeft(), getTop(), mBitmapPaint);

        // 保存图片位置
        canvas.save();

        // 旋转图片
        canvas.rotate(90);
        // 设置字体颜色和大小
        mBitmapPaint.setColor(Color.BLUE);
        mBitmapPaint.setTextSize(30);
        // 绘制文字
        canvas.drawText("从小工到专家",getLeft()+50,getTop()-50,mBitmapPaint);
        // 恢复之前保存的画布的状态
        canvas.restore();
    }


    //Bitmap转Drawable
    public Drawable convertBitmap2Drawable(Bitmap bitmap) {
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
        return bitmapDrawable;
    }


    // Drawable转Bitmap
    public Bitmap convertDrawable2Bitmap(Drawable drawable) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        return bitmapDrawable.getBitmap();
    }

}
