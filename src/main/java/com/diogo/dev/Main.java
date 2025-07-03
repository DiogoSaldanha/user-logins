package com.diogo.dev;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    private static final Map<String, String> CREDENTIALS = new HashMap<>();
    static {
        CREDENTIALS.put("diogo", "senha123");
        CREDENTIALS.put("robert", "password123");
        CREDENTIALS.put("anoosh", "snuffles456");
    }

    public static void main(String[] args) {
//        System.out.println(CREDENTIALS);
//        System.out.println(credentials.get("diogo"));
//        System.out.println(credentials.containsValue("diogo"));
//        System.out.println(credentials.containsKey("diogo"));

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite um username: ");
        String username = scanner.nextLine();

        System.out.println("Digite uma senha: ");
        String password = scanner.nextLine();

        if (isValidCredentials(username, password)) {
            System.out.println("O GRÊMIO É O MAIOR TIME DO BRASIL");
        } else {
            System.out.println("Login e senha inválidos.");
        }

    }

    private static boolean isValidCredentials(String username, String password) {

        if (!CREDENTIALS.containsKey(username)) {
            return false;
        }

        String sha256HashedPassword = DigestUtils.sha256Hex(password);
        String storedUserPassword = CREDENTIALS.get(username);
        String usernameHashedPassword = DigestUtils.sha256Hex(storedUserPassword);

        return CREDENTIALS.containsKey(username) && Objects.equals(usernameHashedPassword, sha256HashedPassword);
    }

}