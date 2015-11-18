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

    public void send(boolean r, boolean g, boolean b) {
        int out = r?1:0;
        out += g?2:0;
        out += b?4:0;
        try {
            this.out.write(out);
            this.out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
