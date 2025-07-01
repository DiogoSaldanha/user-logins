package com.diogo.dev;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("diogo", "senha123");
        credentials.put("robert", "password123");
        credentials.put("anoosh", "snuffles456");
//        System.out.println(credentials);
//        System.out.println(credentials.get("diogo"));
//        System.out.println(credentials.containsValue("diogo"));
//        System.out.println(credentials.containsKey("diogo"));

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite um username: ");
        String username = scanner.nextLine();

        System.out.println("Digite uma senha: ");
        String password = scanner.nextLine();

        if (isValidCredentials(username, password, credentials)) {
            System.out.println("O GRÊMIO É O MAIOR TIME DO BRASIL");
        } else {
            System.out.println("Login e senha inválidos.");
        }

    }

    private static boolean isValidCredentials(String username, String password, Map<String, String> credentials) {

        return credentials.containsKey(username) && Objects.equals(credentials.get(username), password);

    }

}