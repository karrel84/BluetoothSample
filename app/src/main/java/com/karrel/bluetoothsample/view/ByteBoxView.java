package com.karrel.bluetoothsample.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.karrel.bluetoothsample.R;
import com.karrel.bluetoothsample.model.ReadDataItem;

import java.util.List;

/**
 * Created by Rell on 2017. 8. 29..
 */

public class ByteBoxView extends View {
    private ReadDataItem data;
    private int itemSize = 14;
    private int contentTextColor;
    private int countTextColor;
    private int contentBoxColor;
    private int countBoxColor;

    public ByteBoxView(Context context) {
        super(context);
        init();
    }

    public ByteBoxView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ByteBoxView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        countTextColor = ContextCompat.getColor(getContext(), R.color.colorPrimary);
        countBoxColor = ContextCompat.getColor(getContext(), R.color.colorPrimary);

        contentTextColor = ContextCompat.getColor(getContext(), R.color.normalTextColor);
        contentBoxColor = ContextCompat.getColor(getContext(), R.color.normalTextColor);

        itemSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, itemSize, getResources().getDisplayMetrics());
    }

    public void setCountColor(int resColor) {
        countTextColor = ContextCompat.getColor(getContext(), resColor);
        countBoxColor = ContextCompat.getColor(getContext(), resColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int row = 0;
        int column = 0;

        List<String> list = data.list;
        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;
        int offsetX = 0;
        int offsetY = 0;

        for (int i = 0; i < list.size(); i++) {
            if (i != 0 && i % 20 == 0) {
                row++;
                column = 0;
                offsetX = 0;
                offsetY += 8;
            }
            if (i % 5 == 0) {
                column++;
            }

            left = column * itemSize + offsetX;
            top = row * 2 * itemSize + offsetY;
            right = left + itemSize;
            bottom = top + itemSize;

            drawCountBox(canvas, left, top, right, bottom);
            drawContentBox(canvas, left, top + itemSize + 2, right, bottom + itemSize + 2);

            drawCountText(canvas, i, left, bottom);
            drawContentText(canvas, i, left, bottom + itemSize + 2);

            column++;
            offsetX++;
        }
        resizeHeight(bottom + itemSize + offsetY);
        super.onDraw(canvas);
    }

    private void resizeHeight(int bottom) {
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = bottom;
        requestLayout();
    }

    private void drawContentText(Canvas canvas, int i, int left, int bottom) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(contentTextColor);
        paint.setTextSize(itemSize * 0.7f);
        canvas.drawText(data.list.get(i), left + (itemSize * 0.1f), bottom - (itemSize * 0.2f), paint);
    }

    private void drawCountText(Canvas canvas, int i, int left, int bottom) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(countTextColor);
        paint.setTextSize(itemSize * 0.7f);
        canvas.drawText(i + "", left + (itemSize * 0.1f), bottom - (itemSize * 0.2f), paint);
    }

    private void drawContentBox(Canvas canvas, int left, int top, int right, int bottom) {
        Rect rect = new Rect(left, top, right, bottom);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(contentBoxColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        canvas.drawRect(rect, paint);
    }

    private void drawCountBox(Canvas canvas, int left, int top, int right, int bottom) {
        Rect rect = new Rect(left, top, right, bottom);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(countBoxColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        canvas.drawRect(rect, paint);
    }

    public void setData(ReadDataItem data) {
        this.data = data;
    }
}
