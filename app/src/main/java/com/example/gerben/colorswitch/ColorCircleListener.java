package com.example.gerben.colorswitch;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by gerben on 17-11-15.
 */
public class ColorCircleListener implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, ConnectionManager.OnConnectionStatusChangeListener {
    private AppCompatActivity activity;
    short brightness = 255;

    public ColorCircleListener(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        AsyncSend asyncSend = new AsyncSend();
        short[] rgb = ((ColorInputView) v).getRGB();
        activity.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff000000 + (rgb[0] << 16) + (rgb[1] << 8) + rgb[2]));
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(true);
        rgb = Util.dimRGB(rgb, brightness);
        System.out.printf("%h\n", (rgb[0] << 16) + (rgb[1] << 8) + rgb[2]);


        asyncSend.execute(rgb[0], rgb[1], rgb[2]);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        brightness = (short) progress;
        activity.findViewById(R.id.view).callOnClick();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        activity.findViewById(R.id.view).callOnClick();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        activity.findViewById(R.id.view).callOnClick();
    }

    @Override
    public void onConnectionStatusChange(int status) {
        String statusText;
        switch (status) {
            case ConnectionManager.NOT_CONNECTED:
                statusText = "Not connected";
                break;
            case ConnectionManager.CONNECTING:
                statusText = "Connecting...";
                break;
            case ConnectionManager.CONNECTED:
                statusText = "Connected";
                break;
            default:
                statusText = "Undefined status, this should NEVER happen!";
                break;
        }
        ((TextView) activity.findViewById(R.id.textView)).setText(("Status: " + statusText));
    }
}
