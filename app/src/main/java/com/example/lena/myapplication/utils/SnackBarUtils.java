package com.example.lena.myapplication.utils;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by long on 2016/12/29.
 * SnackBar 工具
 */
public final class SnackBarUtils {

    private SnackBarUtils() {
        throw new AssertionError();
    }

    /**
     * 显示 SnackBar
     * @param activity
     * @param message
     * @param isLong
     */
    public static void showSnackBar(Activity activity, String message, boolean isLong) {
        if (activity == null) {
            return;
        }
        View view = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar.make(view, message, isLong ? Snackbar.LENGTH_LONG : Snackbar.LENGTH_SHORT).show();
    }

}
