package com.company.main.Server;

import com.company.main.action.Action;
import com.company.main.builder.action.ActionBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    static ExecutorService executeIt = Executors.newFixedThreadPool(40);
    private static ActionBuilder actionBuilder = new ActionBuilder();
    private static ArrayList<ClientThread> threads = new ArrayList<>();
    private static HashMap<String, Action> action = actionBuilder.getAction();
    private static HashMap<String, String> type = actionBuilder.getType();
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(3345);
        while (!server.isClosed()) {
            Socket client = server.accept();
            ClientThread thread = new ClientThread(client);
            threads.add(thread);
            executeIt.execute(thread);
        }
        executeIt.shutdown();
    }

    public static ActionBuilder getActionBuilder() {
        return actionBuilder;
    }

    public static void setActionBuilder(ActionBuilder actionBuilder) {
        Server.actionBuilder = actionBuilder;
    }

    public static ArrayList<ClientThread> getThreads() {
        return threads;
    }

    public static void setThreads(ArrayList<ClientThread> threads) {
        Server.threads = threads;
    }

    public static HashMap<String, Action> getAction() {
        return action;
    }

    public static void setAction(HashMap<String, Action> action) {
        Server.action = action;
    }

    public static ExecutorService getExecuteIt() {
        return executeIt;
    }

    public static void setExecuteIt(ExecutorService executeIt) {
        Server.executeIt = executeIt;
    }

    public static HashMap<String, String> getType() {
        return type;
    }

    public static void setType(HashMap<String, String> type) {
        Server.type = type;
    }


}
