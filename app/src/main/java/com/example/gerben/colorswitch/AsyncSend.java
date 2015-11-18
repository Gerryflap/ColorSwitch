package com.example.gerben.colorswitch;

import android.os.AsyncTask;

/**
 * Created by gerben on 18-11-15.
 */
public class AsyncSend extends AsyncTask<Short, Void, Void> {

    @Override
    protected Void doInBackground(Short... params) {
        if (params.length == 3) {
            ConnectionManager.getInstance().send(params[0]>128, params[1]>128, params[2]>128);
        }
        return null;
    }
}
