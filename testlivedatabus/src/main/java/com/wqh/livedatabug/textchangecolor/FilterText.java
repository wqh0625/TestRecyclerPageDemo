package com.wqh.livedatabug.textchangecolor;

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
import android.util.TypedValue;
import android.view.View;

import com.wqh.livedatabug.R;

public class FilterText extends View {

	private Paint mPaint = null;
	private String mTextValue = "";
	private Rect mBound = null;
	private int mTextSize;
	private int mRectWidth = 20;
	public FilterText(Context context) {
		this(context,null);
	}


	public FilterText(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}



	public FilterText(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		//获取自定义属性的�?
		TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ChangeTextColor);
		mTextValue = array.getString(R.styleable.ChangeTextColor_text_value);
		mTextSize = array.getDimensionPixelSize(R.styleable.ChangeTextColor_text_size, (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
		//TypedArray用完记得回收
		array.recycle();

		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		//设置文本的字体大�?
		mPaint.setTextSize(mTextSize);
		mBound = new Rect();
		//用来计算当前mTextValue的宽度和高度
		mPaint.getTextBounds(mTextValue, 0, mTextValue.length(),mBound);

	}

	/**
	 * 测量当前view的大�?
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);

		int finalWidth = 0;
		int finalHeight = 0;

		if (widthMode == MeasureSpec.EXACTLY) {
			//如果测量模式是EXACTLY类型，则直接加上padding的�??
			finalWidth = widthSize + getPaddingLeft() + getPaddingRight();
		} else {
			finalWidth = getPaddingLeft() + getPaddingRight() + mBound.width();
			if (widthMode == MeasureSpec.AT_MOST) {
				//如果测量模式是AT_MOST，则不能取期望�?�和我们计算出的�?小�??
				finalWidth = Math.min(finalWidth, widthSize);
			}
		}

		if (heightMode == MeasureSpec.EXACTLY) {
			finalHeight = heightSize + getPaddingBottom() + getPaddingTop();
		} else {
			finalHeight = getPaddingTop() + getPaddingBottom() + mBound.height();
			if (heightMode == MeasureSpec.AT_MOST) {
				finalHeight = Math.min(finalHeight, heightSize);
			}
		}
		//记得调用setMeasuredDimension�?
		setMeasuredDimension(finalWidth, finalHeight);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		int width = getWidth();
		int height = getHeight();

		Paint paint = new Paint();
		
		//创建�?个图层，在图层上演示图形混合后的效果
		int sc = canvas.saveLayer(0, 0, width, height, null, Canvas.ALL_SAVE_FLAG);
		Log.v("谁打得过谁的故事",""+mBound);
		//先绘制src
		canvas.drawBitmap(getSrcBitmap(width,height), 0 , 0 , paint);
		//设置Paint的Xfermode
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		//每次绘制之前将dst的宽度加上10个像素，高度保持和src相同
		mRectWidth += 30;
		canvas.drawBitmap(getDesBitmap(mRectWidth,height), 0 , 0, paint);
		//重置当前Xfermode
		paint.setXfermode(null);
		// 还原画布
		canvas.restoreToCount(sc);

		if (mRectWidth < width) {//不断重绘当前view
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			invalidate();
		}


	}

	// 创建一个矩形bitmap，作为src的bitmap
	private Bitmap getSrcBitmap(int w, int h) {
		Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(bm);
		Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
		//绘制边框
		p.setStrokeWidth(3);
		p.setColor(Color.RED);
		p.setStyle(Paint.Style.STROKE);
		c.drawRect(0, 0, w, h, p);
		
		//绘制文字
		p.setColor(Color.BLACK);
		p.setStyle(Paint.Style.FILL);
		p.setTextSize(mTextSize);
		c.drawText(mTextValue,w / 2 - mBound.width() / 2, h / 2 + mBound.height() / 2, p);
		return bm;
	}

	// 创建一个矩形bitmap，作为dst的bitmap
	private Bitmap getDesBitmap(int w, int h) {
		//首先创建一个位图
		Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		//创建一个和当前位图相同大小的canvas对象
		Canvas c = new Canvas(bm);
		Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
		p.setColor(Color.GREEN);
		c.drawRect(0,0,w,h ,p);
		return bm;
	}


}
