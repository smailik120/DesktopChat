package com.company.main.builder.frame;

import com.company.main.model.User;
import com.company.main.request.SendMessage;
import com.company.main.response.MessageResponse;
import com.company.main.builder.Builder;
import com.google.gson.Gson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;

public class ChatFrame implements Builder {
    private JFrame frame;
    private User user;
    private Socket socket;

    public ChatFrame(User user, Socket socket) throws IOException {
        this.user = user;
        this.socket = socket;
        build();
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void build() throws IOException {
        DefaultListModel<String> model = new DefaultListModel<>();
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream inp = new DataInputStream(socket.getInputStream());
        frame = new JFrame("Чат");
        frame.setSize(600, 600);
        JList<String> list = new JList<String>(model);
        JTextField message = new JTextField("введите сообщение");
        JButton button = new JButton("отправить");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gson gson = new Gson();
                SendMessage request = new SendMessage(message.getText(), "message", user.getLogin());
                String json = gson.toJson(request);
                try {
                    out.writeUTF(json);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    out.flush();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        JPanel panel = new JPanel();
        panel.add(message);
        panel.add(button);
        panel.add(new JScrollPane(list));
        frame.setVisible(true);
        frame.setContentPane(panel);
        Gson gson = new Gson();
        while (true) {
            String response = inp.readUTF();
            Map<String, Object> map = gson.fromJson(response, Map.class);
            String result = (String) map.get("response");
            if (result.equals("message")) {
                MessageResponse res = gson.fromJson(response, MessageResponse.class);
                model.addElement(res.getLogin() + ":" + res.getMessage());
            }
        }
    }
}
