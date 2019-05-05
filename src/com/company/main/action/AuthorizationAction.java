package com.company.main.action;

import com.company.main.database.DatabaseConnector;
import com.company.main.model.User;
import com.company.main.request.AuthorizationRequest;
import com.company.main.response.AuthorizationResponse;
import com.company.main.response.ErrorResponse;
import com.company.main.services.UserService;
import com.google.gson.Gson;
public class AuthorizationAction implements Action {
    Gson gson = new Gson();
    @Override
    public String action(String request) throws InterruptedException {
        AuthorizationRequest authorizationRequest = gson.fromJson(request, AuthorizationRequest.class);
        UserService userService = new UserService("users", new DatabaseConnector().getConnection());
        User user = authorizationRequest.getUser();
        if(userService.getUserByName(user) != null) {
            AuthorizationResponse response = new AuthorizationResponse("successAuth");
            return gson.toJson(response);
        }
        else {
            ErrorResponse response = new ErrorResponse("please enter correct login or password");
            return gson.toJson(response);
        }
    }
}
