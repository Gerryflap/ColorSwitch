package com.example.gerben.colorswitch;

import android.app.Activity;
import android.view.View;
import android.widget.Switch;

/**
 * Created by gerben on 17-11-15.
 */
public class SwitchListener implements View.OnClickListener {
    private Activity activity;

    public SwitchListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        AsyncSend asyncSend = new AsyncSend();
        boolean b = ((Switch) activity.findViewById(R.id.switch1)).isChecked();
        boolean g = ((Switch) activity.findViewById(R.id.switch2)).isChecked();
        boolean r = ((Switch) activity.findViewById(R.id.switch3)).isChecked();
        asyncSend.execute(r, g, b);
    }
}
