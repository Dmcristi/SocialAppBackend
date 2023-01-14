package database.config;

import credentials.Credentials;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private Connection connection;

    private DatabaseConnector() {
    }

    private static class DatabaseConnectorHolder{
        public static final DatabaseConnector instance = new DatabaseConnector();
    }

    public static DatabaseConnector getInstance(){
        return DatabaseConnectorHolder.instance;
    }

    public Connection getConnection(){
        if(this.connection == null){
            try{
                this.connection = DriverManager.getConnection(Credentials.URL, Credentials.USERNAME, Credentials.PASSWORD);
            }catch (SQLException e){
                System.out.println("Invalid credentials!");
                e.printStackTrace();
            }
        }
        return this.connection;
    }

}
