package com.diogo.dev;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseInitializer {

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));

        String jdbcUrl = "jdbc:mysql://localhost:3306/?useSSL=false";
        String user = properties.getProperty("mysql_user");
        String password = properties.getProperty("mysql_password");

        String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS login_credentials_rh";
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS login_credentials_rh.users (
                username VARCHAR(50) NOT NULL,
                password_hash VARCHAR(64) NOT NULL
            )
            """;
        String selectFromTableSQL = "select * from login_credentials_rh.users";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, user, password); Statement statement = connection.createStatement()) {

            statement.execute(createDatabaseSQL);
            statement.execute(createTableSQL);
            statement.execute(selectFromTableSQL);

            System.out.println("O Banco e a Tabela foram provisionados com sucesso.");

        } catch (SQLException e) {
            System.err.println("Um erro aconteceu ao conectar e/ou usar comandos no MySQL. Detalhes: ");
            e.printStackTrace();
        }

    }

}
