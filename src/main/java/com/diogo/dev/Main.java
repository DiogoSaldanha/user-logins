package com.diogo.dev;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.*;

public class Main {
    public static void main(String[] args) {
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

        String sha256HashedPassword = DigestUtils.sha256Hex(password);
        User unverifiedUser = new User(username, sha256HashedPassword);

        JsonProcessor jsonProcessor = new JsonProcessor();
        ArrayList<User> users = jsonProcessor.processJson();

        return users.contains(unverifiedUser);

    }

}