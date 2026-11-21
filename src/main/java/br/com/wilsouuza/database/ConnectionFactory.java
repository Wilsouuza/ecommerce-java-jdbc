package br.com.wilsouuza.database;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    private ConnectionFactory(){}


    static {
        try (InputStream in = ConnectionFactory.class.getClassLoader()
                .getResourceAsStream("database.properties")) {
            if (in == null){
                throw new IllegalArgumentException("database.properties not found in classpath");
            }
            Properties props = new Properties();
            props.load(in);
            URL = props.getProperty("db.url");
            USER = props.getProperty("db.user");
            PASSWORD = props.getProperty("db.password");
        } catch (Exception e){
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to database: " + e);
        }
    }





}
