package com.company.main.builder.action;

import com.company.main.action.Action;
import com.company.main.action.AuthorizationAction;
import com.company.main.action.MessageAction;
import com.company.main.builder.Builder;

import java.util.HashMap;

public class ActionBuilder implements Builder {
    HashMap<String, Action> action = new HashMap<>();
    HashMap<String, String> type = new HashMap<>();


    public ActionBuilder() {
        build();
    }
    public HashMap<String, Action> getAction() {
        return action;
    }

    public void setAction(HashMap<String, Action> action) {
        this.action = action;
    }

    @Override
    public void build() {
        action.put("authorization", new AuthorizationAction());
        action.put("message", new MessageAction());
        type.put("authorization", "me");
        type.put("message", "all");
    }

    public HashMap<String, String> getType() {
        return type;
    }

    public void setType(HashMap<String, String> type) {
        this.type = type;
    }
}
