package com.example.gerben.colorswitch;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Switch;

public class SwitchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
        AsyncInit asyncInit = new AsyncInit();
        asyncInit.execute();
        SwitchListener switchListener = new SwitchListener(this);
        this.findViewById(R.id.switch1).setOnClickListener(switchListener);
        this.findViewById(R.id.switch2).setOnClickListener(switchListener);
        this.findViewById(R.id.switch3).setOnClickListener(switchListener);
    }
}
