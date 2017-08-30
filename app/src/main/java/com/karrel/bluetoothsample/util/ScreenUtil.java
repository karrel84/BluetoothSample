package com.karrel.bluetoothsample.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.Pair;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Rell on 2017. 8. 30..
 */

public class ScreenUtil {
    public static Pair<Integer, Integer> getScreenSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        return new Pair<>(width, height);
    }

    public static final float dpToPx(Context context, final int dp) {
        Resources r = context.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
        return px;
    }
}
