package com.diogo.dev;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class DatabaseInitializer {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));

        String jdbcUrl = "jdbc:mysql://localhost:3306/?useSSL=false";
        String user = properties.getProperty("mysql_user");
        String password = properties.getProperty("mysql_password");

        try (Connection connection = DriverManager.getConnection(jdbcUrl, user, password); Statement statement = connection.createStatement()) {

            // Check if Database already exists:
            String checkDbSQL = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'login_credentials_rh'";
            ResultSet dbResult = statement.executeQuery(checkDbSQL);

            if (dbResult.next()) {
                System.out.println("O banco 'login_credentials_rh' já existe.");

                // This counts the number of tables
                String countTablesSQL = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'login_credentials_rh'";
                ResultSet tableCounter =  statement.executeQuery(countTablesSQL);
                tableCounter.next();
                System.out.println("Quantidade de tabelas no banco: " + tableCounter.getInt(1));


                //Kinda ugly code below, but it works. I was having problems using Statements, need to find a better solution to this

                // This is listing the tables and counting rows and columns
                ResultSet tables = statement.executeQuery("SHOW TABLES FROM login_credentials_rh");

                List<String> tableNames = new ArrayList<>();
                while (tables.next()) {
                    tableNames.add(tables.getString(1));
                }

                for (String tableName: tableNames) {

                    //Counting rows
                    String countRowsSQL = "SELECT COUNT(*) FROM login_credentials_rh." + tableName;
                    ResultSet rowCount = statement.executeQuery(countRowsSQL);
                    rowCount.next();
                    int rows = rowCount.getInt(1);

                    //Counting columns
                    String countColumnsSQL = """
                            SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS 
                            WHERE TABLE_SCHEMA = 'login_credentials_rh' AND TABLE_NAME = '""" + tableName + "'";
                    ResultSet columnCount = statement.executeQuery(countColumnsSQL);
                    columnCount.next();
                    int columns = columnCount.getInt(1);

                    System.out.println("Tabela '" + tableName + "' -> Linhas: " + rows + "; Colunas: " + columns + "'");
                }

                System.out.println("Deseja apagar o banco atual e criar um novo? (s/n): ");
                String answer = scanner.nextLine();

                if (answer.equalsIgnoreCase("s")) {
                    //Deletes DB
                    statement.execute("DROP DATABASE login_credentials_rh");
                    System.out.println("O Banco foi apagado com sucesso.");
                } else {
                    System.out.println("A operação foi cancelada. Nenhuma modificação foi feita!");
                    return;
                }

            }

            //Just creating DB and Table
            String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS login_credentials_rh";
            statement.execute(createDatabaseSQL);

            String createTableSQL = """
            CREATE TABLE IF NOT EXISTS login_credentials_rh.users (
                username VARCHAR(50) NOT NULL,
                password_hash VARCHAR(64) NOT NULL
            )
            """;
            statement.execute(createTableSQL);

            System.out.println("O Banco e a Tabela foram provisionados com sucesso.");

        } catch (SQLException e) {
            System.err.println("Um erro aconteceu ao conectar e/ou usar comandos no MySQL. Detalhes: ");
            e.printStackTrace();
        }
    }
}