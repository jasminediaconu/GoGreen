package client.loginscreen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    private DBConnect() {

    }


    public static DBConnect getInstance() {

        return new DBConnect();

    }

    public Connection getConnection() {
        String connectString = "jdbc:sqlite:data.sqlite";


        Connection connection = null;

        try {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(connectString);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }


}
