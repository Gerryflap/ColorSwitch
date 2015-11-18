package com.example.gerben.colorswitch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SwitchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
        AsyncInit asyncInit = new AsyncInit();
        asyncInit.execute();
        ColorCircleListener colorCircleListener = new ColorCircleListener(this);
        this.findViewById(R.id.view).setOnClickListener(colorCircleListener);

    }
}
