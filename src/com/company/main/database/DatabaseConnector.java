package com.company.main.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnector {
    private Connection conn = null;
    public DatabaseConnector() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false","root","03091977s");
        }
        catch(Exception e1) {
            System.out.print(e1.toString());
        }
    }

    public Connection getConnection() {
        return this.conn;
    }
}
