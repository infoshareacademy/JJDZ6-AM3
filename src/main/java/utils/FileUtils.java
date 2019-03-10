package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import user.model.Users;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Paths;

public class FileUtils {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String USERS_JSON_FILE = Paths.get( "Users.json").toString();


    public static void saveUsersToJsonFile(Users users) {
        try (Writer writer = new FileWriter(USERS_JSON_FILE)) {
            GSON.toJson(users, writer);
            System.out.println("Users saved to json file:  " + USERS_JSON_FILE);
        } catch (IOException e) {
            System.out.println("Exception during saving json file: " + e.getMessage());
        }
    }

    public static Users readUsersJsonFile() {
        try (Reader reader = new FileReader(USERS_JSON_FILE)) {
            System.out.println("Reading Users from json file: " + USERS_JSON_FILE);
            Users users = GSON.fromJson(reader, Users.class);
            System.out.println("Users successfully uploaded. Number of users: " + users.getUsers().size());
            return users;
        } catch (IOException e) {
            System.out.println("Users json file not found or broken: " + e.getMessage());
            return new Users();
        }
    }
}
