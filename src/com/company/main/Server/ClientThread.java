package com.company.main.Server;

import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;

public class ClientThread implements Runnable {
    private Socket client;
    public  Socket getClient() {
        return client;
    }

    public ClientThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            Gson gson = new Gson();
            DataInputStream in = new DataInputStream(client.getInputStream());
            while (!client.isClosed()) {
                String entry = in.readUTF();
                Map<String, Object> map = gson.fromJson(entry, Map.class);
                String name = (String) map.get("name");
                String response = Server.getAction().get(name).action(entry);
                String type = Server.getType().get(name);
                if(type.equals("me")) {
                    sendToMe(response);
                }
                else {
                    sendMessageAll(response);
                }
            }
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendToMe(String message) throws IOException {
        DataOutputStream out = new DataOutputStream(client.getOutputStream());
        out.writeUTF(message);
    }

    public void sendMessageAll(String message) {
        for(ClientThread thread: Server.getThreads()) {
            try {
                    DataOutputStream out = new DataOutputStream(thread.getClient().getOutputStream());
                    out.writeUTF(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
