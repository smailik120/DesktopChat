package com.company.main.client;

import com.company.main.builder.frame.AuthorizationFrame;
import javax.swing.*;
import java.io.IOException;
public class UserApp {
    public static void main(String[] args) throws IOException {
        JFrame frame = new AuthorizationFrame().getFrame();
    }
}
