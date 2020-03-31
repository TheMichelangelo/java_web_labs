package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private final static String USERNAME = "root";
    private final static String PASSWORD = "wrwolf";
    private final static String URL = "jdbc:mysql://localhost:3306/univer";

    public static Connection getNewConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
        return conn;
    }
}
