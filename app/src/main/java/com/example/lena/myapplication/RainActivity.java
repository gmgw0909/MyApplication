package com.example.lena.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

import com.example.lena.myapplication.base.BaseActivity;
import com.example.lena.myapplication.module.wife.WifeFragment;

import java.util.Random;

/**
 * 创建者 LeeBoo
 * 创建时间 2017/5/12
 */

public class RainActivity extends BaseActivity {

    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rain_view);
        relativeLayout = (RelativeLayout) this.findViewById(R.id.content_view);
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            int time = (r.nextInt(400) + 100) * 10;
            RainView rainView = new RainView(this);
            relativeLayout.addView(rainView);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            int left = ((getScreenSize(this)[0] - 20) / 100) * (i + 1);
            lp.setMargins(left, 0, 0, 0);
            rainView.setLayoutParams(lp);
            TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, getScreenSize(this)[1]);
            translateAnimation.setDuration(time);
            translateAnimation.setRepeatCount(-1);
            rainView.startAnimation(translateAnimation);
        }
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RainActivity.this, WifeFragment.class));
                finish();
            }
        });
    }

    public static int[] getScreenSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return new int[]{outMetrics.widthPixels, outMetrics.heightPixels};
    }

}
