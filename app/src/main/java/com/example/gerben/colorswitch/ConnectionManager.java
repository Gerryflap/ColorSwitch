package com.example.gerben.colorswitch;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gerben on 17-11-15.
 */
public class ConnectionManager {
    private DataOutputStream out;
    public static final int NOT_CONNECTED = 0;
    public static final int CONNECTING = 1;
    public static final int CONNECTED = 2;
    private List<OnConnectionStatusChangeListener> listeners;
    private int status = NOT_CONNECTED;
    private int newStatus = NOT_CONNECTED;

    private static ConnectionManager ourInstance = new ConnectionManager();

    public static ConnectionManager getInstance() {
        return ourInstance;
    }

    private ConnectionManager() {
        listeners = new ArrayList<>();

    }

    public void connect() {
        try {
            setStatus(CONNECTING);
            Socket clientSocket = new Socket("192.168.178.88", 1337);
            out = new DataOutputStream(clientSocket.getOutputStream());
            setStatus(CONNECTED);
        } catch (IOException e) {
            e.printStackTrace();
            setStatus(NOT_CONNECTED);
        }
    }

    public void send(short r, short g, short b) {
        try {
            this.out.write(new byte[]{(byte) b, (byte) g, (byte) r});
            this.out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            connect();
        }
    }

    public void setStatus(int status) {
        this.newStatus = status;


    }

    public void addOnConnectionStatusChangeListener(OnConnectionStatusChangeListener listener) {
        listeners.add(listener);
    }

    public void updateStatus() {
        if(newStatus != status) {
            status = newStatus;
            for (OnConnectionStatusChangeListener listener: listeners) {
                listener.onConnectionStatusChange(status);
            }
        }
    }

    public interface OnConnectionStatusChangeListener {
        void onConnectionStatusChange(int status);
    }
}
