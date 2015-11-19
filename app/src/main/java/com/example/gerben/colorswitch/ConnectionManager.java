package com.example.gerben.colorswitch;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by gerben on 17-11-15.
 */
public class ConnectionManager {
    private DataOutputStream out;
    private static ConnectionManager ourInstance = new ConnectionManager();

    public static ConnectionManager getInstance() {
        return ourInstance;
    }

    private ConnectionManager() {
        try {
            Socket clientSocket = new Socket("192.168.178.88", 1337);
            out = new DataOutputStream(clientSocket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(short r, short g, short b) {
        try {
            this.out.write(new byte[]{(byte) b, (byte) g, (byte) r});
            this.out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
