package com.company.main.action;

import com.company.main.request.SendMessage;
import com.company.main.response.MessageResponse;
import com.google.gson.Gson;

import java.io.IOException;

public class MessageAction implements Action {
    @Override
    public String action(String request) throws InterruptedException, IOException {
        Gson gson = new Gson();
        SendMessage sendMessage = gson.fromJson(request, SendMessage.class);
        MessageResponse response = new MessageResponse("message", sendMessage.getMessage(), sendMessage.getLogin());
        //Server.sendMessageAll(gson.toJson(response));
        return gson.toJson(response);
    }
}
