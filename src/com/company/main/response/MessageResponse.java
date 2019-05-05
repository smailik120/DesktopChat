package com.company.main.response;

public class MessageResponse extends Response{
    private String message;
    private String login;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public MessageResponse(String response, String message, String login) {
        super(response);
        this.message = message;
        this.login = login;
    }
}
