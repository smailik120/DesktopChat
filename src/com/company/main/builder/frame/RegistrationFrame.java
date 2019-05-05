package com.company.main.builder.frame;

import com.company.main.database.DatabaseConnector;
import com.company.main.model.User;
import com.company.main.services.UserService;
import com.company.main.builder.Builder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RegistrationFrame implements Builder {
    private JFrame frame;
    public RegistrationFrame() throws IOException {
        build();
    }
    @Override
    public void build() throws IOException {
        frame = new JFrame("Регистрация");
        frame.setSize(900,900);
        JButton button = new JButton("Регистрация");
        JTextField login = new JTextField("enter login");
        JTextField password = new JTextField("enter password");
        login.setSize(50,50);
        password.setSize(50,50);
        JLabel error = new JLabel();
        JPanel contents = new JPanel();
        JPanel label = new JPanel();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseConnector connector = new DatabaseConnector();
                UserService userService = new UserService("users", connector.getConnection());
                String result = userService.addUser(new User(login.getText(), password.getText()));
                if(!error.equals("User add")) {
                    error.setText(result);
                }
            }
        });
        contents.add(login);
        contents.add(password);
        contents.add(button);
        contents.add(error);
        contents.setSize(400,400);
        frame.setVisible(true);
        frame.setContentPane(contents);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
}
