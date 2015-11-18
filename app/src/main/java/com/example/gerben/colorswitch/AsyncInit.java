package com.example.gerben.colorswitch;

import android.os.AsyncTask;

/**
 * Created by gerben on 17-11-15.
 */
public class AsyncInit extends AsyncTask<Void, Void, Void> {
    @Override
    protected Void doInBackground(Void... params) {
        ConnectionManager.getInstance();
        return null;
    }
}
