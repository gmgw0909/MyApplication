package com.example.lena.myapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lena.myapplication.R;

/**
 * SnackBar 工具
 */
public final class SnackBarUtils {

    public static final int LENGTH_SHORT = Snackbar.LENGTH_SHORT;

    public static final int LENGTH_LONG = Snackbar.LENGTH_LONG;

    public static final int LENGTH_INDEFINITE = Snackbar.LENGTH_INDEFINITE;

    public static void ShowNormal(@NonNull Activity activity, @NonNull CharSequence text) {
        View view = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar.make(view, text, LENGTH_SHORT).show();
    }

    public static void ShowInfo(@NonNull Activity activity, @NonNull CharSequence text) {
        View view = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        info(view, text, LENGTH_SHORT).show();
    }

    public static void ShowWarning(@NonNull Activity activity, @NonNull CharSequence text) {
        View view = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        warning(view, text, LENGTH_SHORT).show();
    }

    public static void ShowError(@NonNull Activity activity, @NonNull CharSequence text) {
        View view = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        error(view, text, LENGTH_SHORT).show();
    }

    public static void ShowSucesss(@NonNull Activity activity, @NonNull CharSequence text) {
        View view = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        success(view, text, LENGTH_SHORT).show();
    }

    public static Snackbar success(@NonNull View view, @NonNull CharSequence text, int duration) {
        Context context = view.getContext();
        return make(view,
                text,
                // DO NOT use the resource id directly.
                // It should be a resolved drawable or color.
                ContextCompat.getDrawable(context, R.drawable.ic_success_white_24dp),
                // getResources().getColor() is deprecated.
                ContextCompat.getColor(context, R.color.color_success),
                ContextCompat.getColor(context, android.R.color.white),
                duration);
    }


    public static Snackbar error(@NonNull View view, @NonNull CharSequence text, int duration) {
        Context context = view.getContext();
        return make(view,
                text,
                // DO NOT use the resource id directly.
                // It should be a resolved drawable or color.
                ContextCompat.getDrawable(context, R.drawable.ic_error_24dp),
                // getResources().getColor() is deprecated.
                ContextCompat.getColor(context, R.color.color_error),
                ContextCompat.getColor(context, android.R.color.white),
                duration);
    }

    public static Snackbar info(@NonNull View view, @NonNull CharSequence text, int duration) {
        Context context = view.getContext();
        return make(view,
                text,
                // DO NOT use the resource id directly.
                // It should be a resolved drawable or color.
                ContextCompat.getDrawable(context, R.drawable.ic_info_outline_white_24dp),
                // getResources().getColor() is deprecated.
                ContextCompat.getColor(context, R.color.color_info),
                ContextCompat.getColor(context, android.R.color.white),
                duration);
    }

    public static Snackbar warning(@NonNull View view, @NonNull CharSequence text, int duration) {
        Context context = view.getContext();
        return make(view,
                text,
                // DO NOT use the resource id directly.
                // It should be a resolved drawable or color.
                ContextCompat.getDrawable(context, R.drawable.ic_warning_outline_white_24dp),
                // getResources().getColor() is deprecated.
                ContextCompat.getColor(context, R.color.color_warning),
                ContextCompat.getColor(context, android.R.color.white),
                duration);
    }

    public static Snackbar make(@NonNull View view, @NonNull CharSequence text, Drawable textIcon,
                                @ColorInt int backgroundColor, @ColorInt int textColor, int duration) {
        // Get a usual Snackbar
        Snackbar snackbar = Snackbar.make(view, text, duration);

        // Get the view of it.
        View mView = snackbar.getView();
        // Change the background color.
        mView.setBackgroundColor(backgroundColor);

        // Get the TextView of message.
        TextView textView = (TextView) mView.findViewById(android.support.design.R.id.snackbar_text);
        // Set the left icon of message.
        textView.setCompoundDrawablesWithIntrinsicBounds(textIcon, null, null, null);
        // Set the padding between message and icon.
        textView.setCompoundDrawablePadding(16);
        // To make icon and message aligned.
        textView.setGravity(Gravity.CENTER);
        // Change color of message text.
        textView.setTextColor(textColor);

        return snackbar;
    }


    public static Snackbar make(@NonNull View view, @NonNull CharSequence text, Drawable textIcon,
                                @ColorInt int backgroundColor, @ColorInt int textColor,
                                int duration, Drawable actionIcon, @ColorInt int actionTextColor) {
        // Call the {@link Light#make(View, CharSequence, Drawable, int, int, int)}
        // and get the Snackbar whose message text is customized.
        Snackbar snackbar = make(view, text, textIcon, backgroundColor, textColor, duration);

        // Get the button of action.
        Button actionButton = (Button) snackbar.getView().findViewById(android.support.design.R.id.snackbar_action);
        // Same to TextView of message, set the left icon of action.
        actionButton.setCompoundDrawablesWithIntrinsicBounds(actionIcon, null, null, null);
        // Set the padding between action text and icon.
        actionButton.setCompoundDrawablePadding(16);
        // To make icon and action text aligned.
        actionButton.setGravity(Gravity.CENTER);
        // Change color of action text.
        actionButton.setTextColor(actionTextColor);

        return snackbar;
    }


    public static Snackbar make(@NonNull View view, @StringRes int textRes, @DrawableRes int textIconRes,
                                @ColorRes int backgroundColorRes, @ColorRes int textColorRes, int duration) {
        Context context = view.getContext();
        return make(view,
                context.getString(textRes),
                // DO NOT use the resource id directly.
                // It should be a resolved drawable or color.
                ContextCompat.getDrawable(context, textIconRes),
                // getResources().getColor() is deprecated.
                ContextCompat.getColor(context, backgroundColorRes),
                ContextCompat.getColor(context, textColorRes),
                duration);
    }


    public static Snackbar make(@NonNull View view, @StringRes int textRes, @DrawableRes int textIconRes,
                                @ColorRes int backgroundColorRes, @ColorRes int textColorRes,
                                int duration, @DrawableRes int actionIconRes, @ColorRes int actionTextColorRes) {
        Context context = view.getContext();
        return make(view,
                context.getString(textRes),
                // DO NOT use the resource id directly.
                // It should be a resolved drawable or color.
                ContextCompat.getDrawable(context, textIconRes),
                // getResources().getColor() is deprecated.
                ContextCompat.getColor(context, backgroundColorRes),
                ContextCompat.getColor(context, textColorRes),
                duration,
                ContextCompat.getDrawable(context, actionIconRes),
                ContextCompat.getColor(context, actionTextColorRes));
    }

    public static Snackbar make(@NonNull View view, @NonNull CharSequence text, @DrawableRes int textIconRes,
                                @ColorRes int backgroundColorRes, @ColorRes int textColorRes, int duration) {
        Context context = view.getContext();
        return make(view,
                text,
                // DO NOT use the resource id directly.
                // It should be a resolved drawable or color.
                ContextCompat.getDrawable(context, textIconRes),
                // getResources().getColor() is deprecated.
                ContextCompat.getColor(context, backgroundColorRes),
                ContextCompat.getColor(context, textColorRes),
                duration);
    }


    public static Snackbar make(@NonNull View view, @NonNull CharSequence text, @DrawableRes int textIconRes,
                                @ColorRes int backgroundColorRes, @ColorRes int textColorRes,
                                int duration, @DrawableRes int actionIconRes, @ColorRes int actionTextColorRes) {
        Context context = view.getContext();
        return make(view,
                text,
                // DO NOT use the resource id directly.
                // It should be a resolved drawable or color.
                ContextCompat.getDrawable(context, textIconRes),
                // getResources().getColor() is deprecated.
                ContextCompat.getColor(context, backgroundColorRes),
                ContextCompat.getColor(context, textColorRes),
                duration,
                ContextCompat.getDrawable(context, actionIconRes),
                ContextCompat.getColor(context, actionTextColorRes));
    }

}


