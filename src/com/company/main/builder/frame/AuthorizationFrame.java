package com.company.main.builder.frame;

import com.company.main.model.User;
import com.company.main.request.AuthorizationRequest;
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

public class AuthorizationFrame implements Builder {
    private JFrame frame;
    public AuthorizationFrame() throws IOException {
        build();
    }
    @Override
    public void build() throws IOException {
        RegistrationFrame registrationFrame = new RegistrationFrame();
        DataOutputStream out;
        DataInputStream inp;
        Socket socket = new Socket("localhost",3345);
        out = new DataOutputStream(socket.getOutputStream());
        inp = new DataInputStream(socket.getInputStream());
        frame = new JFrame("Авторизация");
        frame.setSize(600,600);
        JButton button = new JButton("Авторизация");
        JTextField login = new JTextField("enter login");
        JTextField password = new JTextField("enter password");
        JLabel error = new JLabel();
        JPanel contents = new JPanel();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Gson gson = new Gson();
                    User user = new User(login.getText(), password.getText());
                    AuthorizationRequest request = new AuthorizationRequest((user), "authorization");
                    String json = gson.toJson(request);
                    out.writeUTF(json);
                    out.flush();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        contents.add(login);
        contents.add(password);
        contents.add(button);
        contents.add(error);
        frame.setVisible(true);
        frame.setContentPane(contents);
        contents.setSize(200,200);
        Gson gson = new Gson();
        while(true) {
            String response = inp.readUTF();
            System.out.println(response);
            User user = new User(login.getText(), password.getText());
            Map<String, Object> map = gson.fromJson(response, Map.class);
            String result = (String) map.get("response");
            System.out.print(result);
            if(result.equals("successAuth")) {
                frame.setVisible(false);
                ChatFrame chat = new ChatFrame(user, socket);
                break;
            }
            else {
                error.setText(result);
            }
        }
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
}
