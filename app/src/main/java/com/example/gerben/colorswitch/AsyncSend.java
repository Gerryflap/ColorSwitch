package com.example.gerben.colorswitch;

import android.os.AsyncTask;

/**
 * Created by gerben on 18-11-15.
 */
public class AsyncSend extends AsyncTask<Boolean, Void, Void> {

    @Override
    protected Void doInBackground(Boolean... params) {
        if (params.length == 3) {
            ConnectionManager.getInstance().send(params[0], params[1], params[2]);
        }
        return null;
    }
}
