package com.company.main.action;

import java.io.IOException;

public interface Action {
   public String action(String request) throws InterruptedException, IOException;
}
