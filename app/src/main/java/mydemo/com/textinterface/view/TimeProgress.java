package mydemo.com.textinterface.view;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

//import com.xtc.happyword.R;

import mydemo.com.textinterface.R;

/**
 * 时间进度条
 * Created by @author WangHaoFei on 2018/9/13.
 */

public class TimeProgress extends View {

    private static final int LINE_WIDTH = 6;
    private static final int HALF_LINE_WIDTH = 4;
    private static final int HALF_MAX_PROGRESS = 50;
    private static final int HALF_PI = 180;

    public Paint paint;
    private Paint innerPaint;
    private int progress;

    private int halfStraightLineLength;
    private int halfCircleProgress;
    private int halfStraightLineProgress;

    private Bitmap bitmap;

    public TimeProgress(Context context) {
        this(context, null);
    }

    public TimeProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(this.getResources().getColor(R.color.progress_line));
        paint.setStrokeWidth(LINE_WIDTH);

        innerPaint = new Paint();
        innerPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        innerPaint.setAntiAlias(true);
        innerPaint.setDither(true);

        progress = 100;
        Resources resources = context.getResources();
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_background);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        int circleLength = (int) Math.round(Math.PI * getHeight());
        this.halfStraightLineLength = getWidth() - getHeight();
        int straightLineLength = halfStraightLineLength * 2;
        int sumLength = straightLineLength + circleLength;

        int circleProgress = Math.round((circleLength / (float) sumLength) * 100);
        this.halfCircleProgress = Math.round(circleProgress / 2);

        int straightLineProgress = 100 - circleProgress;
        this.halfStraightLineProgress = Math.round(straightLineProgress / 2.0f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        drawBackground(canvas);
        if (progress <= halfStraightLineProgress) {
            drawProgressOne(canvas);
        } else if (progress <= HALF_MAX_PROGRESS) {
            drawProgressTwo(canvas);
        } else if (progress <= (HALF_MAX_PROGRESS + halfStraightLineProgress)) {
            drawProgressThree(canvas);
        } else {
            drawProgressFour(canvas);
        }
    }

    private void drawProgressOne(Canvas canvas) {
        float scale = progress / (float) halfStraightLineProgress;
        int length = (int) (halfStraightLineLength * scale);
        int startX = halfStraightLineLength + getHeight() / 2;
        canvas.drawLine(startX, HALF_LINE_WIDTH, startX - length, HALF_LINE_WIDTH, paint);
    }

    private void drawProgressTwo(Canvas canvas) {
        drawTopLine(canvas);

        float scale = (progress - halfStraightLineProgress) / (float) halfCircleProgress;
        float angle = scale * HALF_PI;
        canvas.drawArc(HALF_LINE_WIDTH, HALF_LINE_WIDTH, getHeight() - HALF_LINE_WIDTH,
                getHeight() - HALF_LINE_WIDTH, -90, -angle, false, paint);
    }

    private void drawProgressThree(Canvas canvas) {
        drawTopLine(canvas);
        drawLeftArc(canvas);

        float scale = (progress - HALF_MAX_PROGRESS) / (float) halfStraightLineProgress;
        int length = (int) (halfStraightLineLength * scale);
        int startX = getHeight() / 2;
        canvas.drawLine(startX, getHeight() - HALF_LINE_WIDTH, startX + length,
                getHeight() - HALF_LINE_WIDTH, paint);
    }

    private void drawProgressFour(Canvas canvas) {
        drawTopLine(canvas);
        drawLeftArc(canvas);
        drawBottomLine(canvas);

        float scale = (progress - HALF_MAX_PROGRESS - halfStraightLineProgress) / (float) halfCircleProgress;
        float angle = scale * HALF_PI;
        canvas.drawArc(getWidth() - getHeight() + LINE_WIDTH / 2, HALF_LINE_WIDTH, getWidth() - HALF_LINE_WIDTH,
                getHeight() - HALF_LINE_WIDTH, 90, -angle, false, paint);
    }

    private void drawTopLine(Canvas canvas) {
        int startX = halfStraightLineLength + getHeight() / 2;
        canvas.drawLine(startX, HALF_LINE_WIDTH, getHeight() / 2, HALF_LINE_WIDTH, paint);
    }

    private void drawLeftArc(Canvas canvas) {
        canvas.drawArc(HALF_LINE_WIDTH, HALF_LINE_WIDTH, getHeight() - HALF_LINE_WIDTH,
                getHeight() - HALF_LINE_WIDTH, -90, -HALF_PI, false, paint);
    }

    private void drawBottomLine(Canvas canvas) {
        int halfHeight = getHeight() / 2;
        canvas.drawLine(halfHeight, getHeight() - HALF_LINE_WIDTH,
                halfHeight + halfStraightLineLength, getHeight() - HALF_LINE_WIDTH, paint);
    }

    private void drawBackground(Canvas canvas) {
        canvas.drawBitmap(bitmap, 0, 0, innerPaint);
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }
}
