package com.company.main.request;

public class SendMessage extends Request{
    private String message;
    private String login;
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SendMessage(String message, String name,String login) {
        super(name);
        this.message = message;
        this.login = login;
    }
}
