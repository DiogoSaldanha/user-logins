package com.diogo.dev;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonProcessor {
    public Gson gson;
    public FileReader fileReader;
    public ArrayList<User> users;

    public JsonProcessor() {
        this.gson = new Gson();
    }

    public ArrayList<User> processJson(){

        try {

            this.fileReader = new FileReader("src/main/java/com/diogo/dev/credentials.json");
            Type listType = new TypeToken<List<User>>() {}.getType();
            this.users = this.gson.fromJson(fileReader, listType);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return this.users;

    }

}
