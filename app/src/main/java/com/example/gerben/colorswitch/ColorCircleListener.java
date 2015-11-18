package com.example.gerben.colorswitch;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;

/**
 * Created by gerben on 17-11-15.
 */
public class ColorCircleListener implements View.OnClickListener {
    private AppCompatActivity activity;

    public ColorCircleListener(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        AsyncSend asyncSend = new AsyncSend();
        short[] rgb = ((ColorInputView) v).getRGB();
        System.out.printf("%h\n", (rgb[0] << 16) +  (rgb[1] << 8) + rgb[2]);
        activity.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff000000 + (rgb[0] << 16) + (rgb[1] << 8) + rgb[2]));
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(true);

        asyncSend.execute(rgb[0], rgb[1], rgb[2]);
    }

}
