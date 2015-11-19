package com.example.gerben.colorswitch;

import android.os.AsyncTask;

/**
 * Created by gerben on 17-11-15.
 */
public class AsyncInit extends AsyncTask<Void, Void, Void> {
    protected void onPreExecute() {
        ConnectionManager.getInstance().setStatus(ConnectionManager.CONNECTING);
        ConnectionManager.getInstance().updateStatus();
    }

    @Override
    protected Void doInBackground(Void... params) {
        ConnectionManager.getInstance().connect();
        return null;
    }

    protected void onPostExecute(Void _) {
        ConnectionManager.getInstance().updateStatus();
    }
}
