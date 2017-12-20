package com.example.lena.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 创建者 LeeBoo
 * 创建时间 2017/11/28
 */

public class RainView extends View {
    public RainView(Context context) {
        super(context);
    }

    public RainView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RainView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 定义画笔
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        // 消除锯齿
        paint.setAntiAlias(true);
        // 设置画笔的颜色
        paint.setColor(Color.WHITE);
        // 设置paint的外框宽度
        paint.setStrokeWidth(9);
        // 画一个椭圆
        RectF re = new RectF(20, 20, 28, 35);
        canvas.drawOval(re, paint);
    }
}
