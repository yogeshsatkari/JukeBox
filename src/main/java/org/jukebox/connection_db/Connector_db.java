package org.jukebox.connection_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector_db {
    public static Connection con=null;
    public static void establishConnection() throws ClassNotFoundException,SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded successfully.");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox","root","root");
        System.out.println("Connection established.");
    }
}
